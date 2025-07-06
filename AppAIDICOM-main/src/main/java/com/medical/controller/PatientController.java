package com.medical.controller;


import com.medical.service.PatientService;
import com.medical.controller.ApiController.ReportDto;
import com.medical.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/doctor")
public class PatientController {

    @Autowired
    private PatientService patientService;
    

    // Trang chủ bác sĩ
    @GetMapping("/home")
    public String doctorHome(Model model) {
        // Thêm thống kê cho trang chủ
        model.addAttribute("patientCount", patientService.getPatientCount());
        model.addAttribute("todayAppointments", patientService.getTodayAppointments());
        model.addAttribute("pendingImages", patientService.getPendingImagesCount());
        model.addAttribute("unfinishedReports", patientService.getUnfinishedReportsCount());
        return "doctor_home";
    }

    // Danh sách bệnh nhân
    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient_list";
    }

    // Chi tiết bệnh nhân
    @GetMapping("/patient/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient_details";
    }

    // Thêm chú thích
    @PostMapping("/patient/{id}/annotation")
    public String saveAnnotation(@PathVariable Long id,
                               @RequestParam String content,
                               RedirectAttributes redirectAttributes) {
        try {
            patientService.saveAnnotation(id, content, LocalDate.now().toString());
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm chú thích thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm chú thích: " + e.getMessage());
        }
        return "redirect:/doctor/patient/" + id;
    }

    // Tạo báo cáo
    @PostMapping("/patient/{id}/report")
    public String saveReport(@PathVariable Long id,
                           @RequestParam String content,
                           RedirectAttributes redirectAttributes) {
        try {
            patientService.saveReport(id, content, LocalDate.now().toString());
            redirectAttributes.addFlashAttribute("successMessage", "Đã tạo báo cáo thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tạo báo cáo: " + e.getMessage());
        }
        return "redirect:/doctor/patient/" + id;
    }

    // Upload ảnh DICOM
    @PostMapping("/patient/{id}/upload-dicom")
    public String uploadDicom(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        patientService.uploadDicomImage(id, file);
        return "redirect:/doctor/patient/" + id;
    }

    // Lưu kết quả AI
    @PostMapping("/patient/{id}/ai-result")
    public String saveAIResult(@PathVariable Long id,
                             @RequestParam String resultDescription,
                             RedirectAttributes redirectAttributes) {
        try {
            patientService.saveAIResult(id, resultDescription, LocalDate.now().toString());
            redirectAttributes.addFlashAttribute("successMessage", "Đã lưu kết quả AI thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi lưu kết quả AI: " + e.getMessage());
        }
        return "redirect:/doctor/patient/" + id;
    }

    // Trang quản lý lịch hẹn
    @GetMapping("/appointments")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", patientService.getAllAppointments());
        model.addAttribute("todayAppointments", patientService.getTodayAppointments());
        return "appointments";
    }
    

    // Trang quản lý đơn thuốc
    @GetMapping("/prescriptions")
    public String listPrescriptions(Model model) {
        model.addAttribute("prescriptions", patientService.getAllPrescriptions());
        return "prescriptions";
    }

    // Trang hình ảnh y tế
    @GetMapping("/medical-images")
    public String listMedicalImages(Model model) {
        model.addAttribute("images", patientService.getAllDicomImages());
        return "medical_images";
    }

    // Trang phân tích AI
    @GetMapping("/ai-analysis")
    public String listAIResults(Model model) {
        model.addAttribute("aiResults", patientService.getAllAIResults());
        return "ai_analysis";
    }

    // Trang tạo báo cáo
    @GetMapping("/reports")
    public String showReportsPage(@RequestParam(required = false) Long patientId, Model model) {
        if (patientId != null) {
            Patient patient = patientService.getPatientById(patientId);
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", new Patient());
        }
        model.addAttribute("report", new ReportDto());
        return "report_creation";
    }

    @PostMapping("/reports/save")
    public String saveReport(@ModelAttribute ReportDto report,
                           @RequestParam Long patientId,
                           RedirectAttributes redirectAttributes) {
        try {
            patientService.saveReportFromDto(patientId, report);
            redirectAttributes.addFlashAttribute("message", "Báo cáo đã được lưu thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu báo cáo: " + e.getMessage());
        }
        return "redirect:/doctor/patient/" + patientId;
    }

    // Các phương thức mới để hỗ trợ các trang mới
    @PostMapping("/appointments/add")
    public String addAppointment(@RequestParam Long patientId,
                               @RequestParam String date,
                               @RequestParam String time,
                               @RequestParam String reason,
                               RedirectAttributes redirectAttributes) {
        patientService.addAppointment(patientId, date, time, reason);
        redirectAttributes.addFlashAttribute("success", "Đã thêm lịch hẹn thành công");
        return "redirect:/doctor/appointments";
    }


    @PostMapping("/prescriptions/add")
    public String addPrescription(@RequestParam Long patientId,
                                @RequestParam String diagnosis,
                                @RequestParam String medicines,
                                RedirectAttributes redirectAttributes) {
        try {
            patientService.addPrescription(patientId, diagnosis, medicines);
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm đơn thuốc thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm đơn thuốc: " + e.getMessage());
        }
        return "redirect:/doctor/prescriptions";
    }

    @PostMapping("/ai/analyze")
    public String analyzeImage(@RequestParam Long imageId,
                             @RequestParam String analysisType,
                             RedirectAttributes redirectAttributes) {
        try {
            patientService.analyzeImage(imageId, analysisType);
            redirectAttributes.addFlashAttribute("successMessage", "Đã bắt đầu phân tích ảnh");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi phân tích ảnh: " + e.getMessage());
        }
        return "redirect:/doctor/ai-analysis";
    }
}