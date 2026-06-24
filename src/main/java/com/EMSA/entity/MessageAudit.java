package com.EMSA.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class MessageAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private String stepName;
    private String status;
    private String errorReason;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getStepName() { return stepName; }
    public String getStatus() { return status; }
    public String getErrorReason() { return errorReason; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setStepName(String stepName) { this.stepName = stepName; }
    public void setStatus(String status) { this.status = status; }
    public void setErrorReason(String errorReason) { this.errorReason = errorReason; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}