package com.medical.controller;

import com.medical.controller.ApiController.ReportDto;
import com.medical.model.*;
import com.medical.service.PatientService;
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

    @GetMapping("/patient/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient_details";
    }

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
    
    @PostMapping("/patient/{id}/upload-dicom")
    public String uploadDicom(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        patientService.uploadDicomImage(id, file);
        return "redirect:/doctor/patient/" + id;
    }
    
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
    @GetMapping("/home")
    public String doctorHome(Model model) {
        // Thêm các thông tin cần thiết cho trang chủ
        return "doctor_home";
    }
    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient_list";
    }
    @GetMapping("/reports")
public String showReportsPage(@RequestParam Long patientId, Model model) {
    Patient patient = patientService.getPatientById(patientId); // Lấy thật từ DB nếu có
    model.addAttribute("patient", patient);
    model.addAttribute("report", new ReportDto()); // <-- Đảm bảo đối tượng report tồn tại
    return "report_creation";
}
@PostMapping("/reports/save")
public String saveReport(@ModelAttribute ReportDto report,
                         @RequestParam Long patientId,
                         RedirectAttributes redirectAttributes) {
    // Ví dụ nội dung xử lý bên trong
    
    redirectAttributes.addFlashAttribute("message", "Báo cáo đã được lưu thành công!");
    return "redirect:/doctor/patient/" + patientId;
}

}