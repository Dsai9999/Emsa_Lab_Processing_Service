package com.EMSA.entity;

import jakarta.persistence.*;

@Entity
public class LabResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private String patientName;
    private String testName;
    private String result;
    private String source;
    private String status;

    public Long getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getTestName() { return testName; }
    public String getResult() { return result; }
    public String getSource() { return source; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setTestName(String testName) { this.testName = testName; }
    public void setResult(String result) { this.result = result; }
    public void setSource(String source) { this.source = source; }
    public void setStatus(String status) { this.status = status; }
}