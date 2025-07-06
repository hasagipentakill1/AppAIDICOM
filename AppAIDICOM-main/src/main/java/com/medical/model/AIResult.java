package com.medical.model;

import jakarta.persistence.*;

@Entity
public class AIResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dicom_image_id") // Cột khóa ngoại tham chiếu đến DicomImage
    private DicomImage dicomImage;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String analysisType;
    private String analysisDate;
    private String result;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DicomImage getDicomImage() { return dicomImage; }
    public void setDicomImage(DicomImage dicomImage) { this.dicomImage = dicomImage; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public String getAnalysisType() { return analysisType; }
    public void setAnalysisType(String analysisType) { this.analysisType = analysisType; }

    public String getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(String analysisDate) { this.analysisDate = analysisDate; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}