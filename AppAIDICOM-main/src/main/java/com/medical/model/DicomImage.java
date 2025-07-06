package com.medical.model;

import jakarta.persistence.*;

@Entity
public class DicomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private String annotationLabel;
    private String annotationNotes;
    private String status;

    @ManyToOne
    private Patient patient;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getAnnotationLabel() { return annotationLabel; }
    public void setAnnotationLabel(String annotationLabel) { this.annotationLabel = annotationLabel; }

    public String getAnnotationNotes() { return annotationNotes; }
    public void setAnnotationNotes(String annotationNotes) { this.annotationNotes = annotationNotes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}