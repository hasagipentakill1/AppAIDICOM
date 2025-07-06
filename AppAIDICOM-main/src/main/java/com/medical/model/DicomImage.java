package com.medical.model;

import jakarta.persistence.*;

@Entity
public class DicomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private String captureDate;

    @ManyToOne
    private Patient patient;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getCaptureDate() { return captureDate; }
    public void setCaptureDate(String captureDate) { this.captureDate = captureDate; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
