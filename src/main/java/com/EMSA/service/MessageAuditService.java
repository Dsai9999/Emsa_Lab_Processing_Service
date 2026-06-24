package com.EMSA.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.EMSA.entity.MessageAudit;
import com.EMSA.repository.MessageAuditRepository;

@Service
public class MessageAuditService {

    private final MessageAuditRepository auditRepository;

    public MessageAuditService(MessageAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void saveAudit(String patientId, String stepName, String status, String errorReason) {

        MessageAudit audit = new MessageAudit();

        audit.setPatientId(patientId);
        audit.setStepName(stepName);
        audit.setStatus(status);
        audit.setErrorReason(errorReason);
        audit.setCreatedAt(LocalDateTime.now());

        auditRepository.save(audit);
    }
}