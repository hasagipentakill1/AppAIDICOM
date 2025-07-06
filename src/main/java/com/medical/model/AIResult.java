package com.medical.model;

import jakarta.persistence.*;

@Entity
public class AIResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resultDescription;
    private String analysisDate;

    @ManyToOne
    private Patient patient;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResultDescription() { return resultDescription; }
    public void setResultDescription(String resultDescription) { this.resultDescription = resultDescription; }
    public String getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(String analysisDate) { this.analysisDate = analysisDate; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}