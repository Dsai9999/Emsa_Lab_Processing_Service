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

    public LabResultConsumerService(LabResultRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "lab-results", groupId = "lab-processors")
    public void consumeLabResult(String message) {

        System.out.println("Message received from Kafka: " + message);

        try {
            LabResult labResult = objectMapper.readValue(message, LabResult.class);

            if (labResult.getPatientId() == null || labResult.getPatientId().isEmpty()) {
                labResult.setStatus("FAILED_VALIDATION");
            } else if (labResult.getTestName() == null || labResult.getTestName().isEmpty()) {
                labResult.setStatus("FAILED_VALIDATION");
            } else {
                labResult.setStatus("VALIDATED");
            }

            repository.save(labResult);

            System.out.println("Saved to DB: " + labResult.getPatientId());

        } catch (Exception e) {
            System.out.println("Error processing Kafka message: " + e.getMessage());
        }
    }
}