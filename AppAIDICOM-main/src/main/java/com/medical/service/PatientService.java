package com.medical.service;

import com.medical.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medical.model.Annotation;
import com.medical.model.Report;
import com.medical.model.DicomImage;
import com.medical.model.AIResult;
import com.medical.model.Patient;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DicomImageRepository dicomImageRepository;
    
    @Autowired
    private AIResultRepository aiResultRepository;
    
    @Autowired
    private AnnotationRepository annotationRepository;
    
    @Autowired
    private ReportRepository reportRepository;
    
    @Value("${dicom.storage.path}")
    private String dicomStoragePath;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

     public void saveAnnotation(Long patientId, String content, String createdDate) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));
        
        Annotation annotation = new Annotation();
        annotation.setContent(content);
        annotation.setCreatedDate(createdDate);
        annotation.setPatient(patient);
        
        annotationRepository.save(annotation);
    }


    public void saveReport(Long patientId, String content, String createdDate) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));
        
        Report report = new Report();
        report.setContent(content);
        report.setCreatedDate(createdDate);
        report.setPatient(patient);
        
        reportRepository.save(report);
    }
    
    public void uploadDicomImage(Long patientId, MultipartFile file) throws IOException {
        Patient patient = getPatientById(patientId);
        if (patient != null) {
            // Tạo thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(dicomStoragePath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            // Lưu file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Lưu thông tin vào DB
            DicomImage dicomImage = new DicomImage();
            dicomImage.setFilePath(filePath.toString());
            dicomImage.setCaptureDate(LocalDate.now().toString());
            dicomImage.setPatient(patient);
            dicomImageRepository.save(dicomImage);
        }
    }
    
    public void saveAIResult(Long patientId, String resultDescription, String analysisDate) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));
        
        AIResult aiResult = new AIResult();
        aiResult.setResultDescription(resultDescription);
        aiResult.setAnalysisDate(analysisDate);
        aiResult.setPatient(patient);
        
        aiResultRepository.save(aiResult);
    }
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }
}