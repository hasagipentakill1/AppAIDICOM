package com.medical.controller;

import java.io.ByteArrayOutputStream;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/dicom")
    public ResponseEntity<Resource> getDicomFile(@RequestParam String path) throws IOException {
        Path filePath = Paths.get(path).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/dicom"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            throw new RuntimeException("File not found: " + path);
        }
    }
    
    @GetMapping("/dicom-preview")
    public ResponseEntity<byte[]> getDicomPreview(@RequestParam String path) throws IOException {
        File dicomFile = new File(path);
        DicomImageReader reader = new DicomImageReader(null);
        
        try (DicomInputStream dis = new DicomInputStream(dicomFile)) {
            Attributes attributes = dis.readDataset();
            reader.setInput(dis);
            
            BufferedImage image = reader.read(0, null);
            byte[] imageBytes = toByteArray(image, "png");
            
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageBytes);
        }
    }
    
    @GetMapping("/reports/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Long id) {
        // Implement logic to get report and convert to DTO
        return ResponseEntity.ok(new ReportDto());
    }
    
    private byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
    
    public static class ReportDto {
    private Long id;
    private String title;
    private String content;
    private String createdDate;
    private String patientName;
    private String diagnosis;
    private String treatmentPlan;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    
    public String getTreatmentPlan() { return treatmentPlan; }
    public void setTreatmentPlan(String treatmentPlan) { this.treatmentPlan = treatmentPlan; }
}
}