package com.medical.service;

import com.medical.controller.ApiController;
import com.medical.model.*;
import com.medical.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {
    
    @Autowired private PatientRepository patientRepository;
    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private DicomImageRepository dicomImageRepository;
    @Autowired private AIResultRepository aiResultRepository;
    @Autowired private ReportRepository reportRepository;

    // ===== THỐNG KÊ =====
    public long getPatientCount() {
        return patientRepository.count();
    }

    public long getTodayAppointmentsCount() {
        return appointmentRepository.countByAppointmentDate(LocalDate.now().toString());
    }

    public List<Appointment> getTodayAppointments() {
        return appointmentRepository.findByAppointmentDate(LocalDate.now().toString());
    }
    public List<Patient> getAllPatients() {
    return patientRepository.findAll();
}
    public Patient getPatientById(Long id) {
    return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
}
    
    public void saveReport(Long patientId, String title, String content) {
    Report report = new Report();
    report.setPatient(patientRepository.findById(patientId).orElseThrow());
    report.setTitle(title);
    report.setContent(content);
    report.setCreatedDate(LocalDate.now().toString());
    report.setStatus("DRAFT");
    reportRepository.save(report);
}
    public void saveAnnotation(Long imageId, String label, String notes) {
    DicomImage image = dicomImageRepository.findById(imageId).orElseThrow();
    image.setAnnotationLabel(label);
    image.setAnnotationNotes(notes);
    image.setStatus("ANNOTATED");
    dicomImageRepository.save(image);
}

    public long getPendingImagesCount() {
        return dicomImageRepository.countByStatus("PENDING");
    }

    public long getUnfinishedReportsCount() {
        return reportRepository.countByStatusNot("COMPLETED");
    }

    // ===== LỊCH HẸN =====
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void addAppointment(Long patientId, String date, String time, String reason) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patientRepository.findById(patientId).orElseThrow());
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setReason(reason);
        appointment.setStatus("SCHEDULED");
        appointmentRepository.save(appointment);
    }

public void uploadDicomImage(Long patientId, MultipartFile file) {
    // method implementation
}
public void saveAIResult(Long patientId, String result, String notes) {
    // method implementation
}

    // ===== ĐƠN THUỐC =====
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public void addPrescription(Long patientId, String diagnosis, String medicines) {
        Prescription prescription = new Prescription();
        prescription.setPatient(patientRepository.findById(patientId).orElseThrow());
        prescription.setDiagnosis(diagnosis);
        prescription.setMedicines(medicines);
        prescription.setDate(LocalDate.now().toString());
        prescriptionRepository.save(prescription);
    }

    // ===== HÌNH ẢNH =====
    public List<DicomImage> getAllDicomImages() {
        return dicomImageRepository.findAll();
    }

    // ===== PHÂN TÍCH AI =====
    public List<AIResult> getAllAIResults() {
        return aiResultRepository.findAll();
    }

    public void analyzeImage(Long imageId, String analysisType) {
        AIResult result = new AIResult();
        result.setDicomImage(dicomImageRepository.findById(imageId).orElseThrow());
        result.setAnalysisType(analysisType);
        result.setAnalysisDate(LocalDate.now().toString());
        aiResultRepository.save(result);
    }

    // ===== BÁO CÁO =====
    public void saveReportFromDto(Long patientId, ApiController.ReportDto reportDto) {
        Report report = new Report();
        report.setPatient(patientRepository.findById(patientId).orElseThrow());
        report.setTitle(reportDto.getTitle());
        report.setContent(reportDto.getContent());
        report.setDiagnosis(reportDto.getDiagnosis());
        report.setTreatmentPlan(reportDto.getTreatmentPlan());
        report.setCreatedDate(LocalDate.now().toString());
        report.setStatus("DRAFT");
        reportRepository.save(report);
    }
}