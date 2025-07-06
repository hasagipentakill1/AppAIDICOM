package com.medical.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dateOfBirth;
    private String gender;

    @OneToMany(mappedBy = "patient")
    private List<DicomImage> dicomImages;

    @OneToMany(mappedBy = "patient")
    private List<AIResult> aiResults;

    @OneToMany(mappedBy = "patient")
    private List<Annotation> annotations;

    @OneToMany(mappedBy = "patient")
    private List<Report> reports;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public List<DicomImage> getDicomImages() { return dicomImages; }
    public void setDicomImages(List<DicomImage> dicomImages) { this.dicomImages = dicomImages; }
    public List<AIResult> getAiResults() { return aiResults; }
    public void setAiResults(List<AIResult> aiResults) { this.aiResults = aiResults; }
    public List<Annotation> getAnnotations() { return annotations; }
    public void setAnnotations(List<Annotation> annotations) { this.annotations = annotations; }
    public List<Report> getReports() { return reports; }
    public void setReports(List<Report> reports) { this.reports = reports; }
}