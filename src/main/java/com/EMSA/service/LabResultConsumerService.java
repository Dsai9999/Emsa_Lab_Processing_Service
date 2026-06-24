package com.EMSA.service;

import com.EMSA.entity.LabResult;
import com.EMSA.repository.LabResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service

public class LabResultConsumerService {

    private final LabResultRepository repository;
    private final ObjectMapper objectMapper;
    private final MessageAuditService auditService;

    public LabResultConsumerService(LabResultRepository repository, ObjectMapper objectMapper, MessageAuditService auditService) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.auditService=auditService;
    }
    private void validateLabResult(LabResult labResult) {

  	  if (labResult.getPatientId() == null ||
  		        labResult.getPatientId().isEmpty()) {

  		        labResult.setStatus("FAILED_VALIDATION");
  		    }
  		    else if (labResult.getTestName() == null ||
  		             labResult.getTestName().isEmpty()) {

  		        labResult.setStatus("FAILED_VALIDATION");
  		    }
  		    else if (labResult.getResult() == null ||
  		             labResult.getResult().isEmpty()) {

  		        labResult.setStatus("FAILED_VALIDATION");
  		    }
  		    else {
  		        labResult.setStatus("VALIDATED");
    }
  }

    @KafkaListener(topics = "lab-results", groupId = "lab-processors")
    public void consumeLabResult(String message) {

        System.out.println("Message received from Kafka: " + message);
      
        try {
        	LabResult labResult = objectMapper.readValue(message, LabResult.class);

            auditService.saveAudit(labResult.getPatientId(), "CONSUMED", "SUCCESS", null);

            validateLabResult(labResult);

            auditService.saveAudit(labResult.getPatientId(), "VALIDATED", labResult.getStatus(), null);

            repository.save(labResult);

            auditService.saveAudit(labResult.getPatientId(), "SAVED_TO_DB", "SUCCESS", null);

            System.out.println("Saved to DB: " + labResult.getPatientId());

        } catch (Exception e) {
            System.out.println("Error processing Kafka message: " + e.getMessage());
        }
    }
}