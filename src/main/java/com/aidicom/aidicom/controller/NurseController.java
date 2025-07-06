package com.aidicom.aidicom.controller;

import com.aidicom.aidicom.model.BenhAn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    // Lưu trữ danh sách bệnh án trong bộ nhớ tạm thời
    private List<BenhAn> danhSachBenhAn = new ArrayList<>();

    // Trang chính (dashboard)
    @GetMapping("/home")
    public String homePage() {
        return "nurse/home";
    }

    // DICOM Viewer
    @GetMapping("/dicom-viewer")
    public String dicomViewerPage() {
        return "nurse/dicom-viewer";
    }

    // Phân tích AI
    @GetMapping("/analyze")
    public String analyzeAiPage() {
        return "nurse/analyze_ai";
    }

    // Ghi chú bệnh án
    @GetMapping("/add-notes")
    public String notesPage() {
        return "nurse/add_notes";
    }

    // Chuyển hồ sơ cho bác sĩ
    @GetMapping("/select-doctor")
    public String selectDoctorPage() {
        return "nurse/select_doctor";
    }

    // ✅ FORM tạo hồ sơ bệnh án (GET)
    @GetMapping("/record-form")
    public String hienFormNhapBenhAn(Model model) {
        model.addAttribute("benhAn", new BenhAn());
        return "nurse/create-record"; // Tương ứng với templates/nurse/create-record.html
    }

  
    // ✅ Trang xem danh sách bệnh án dưới dạng card
    @GetMapping("/records")
    public String hienThiDanhSachBenhAn(Model model) {
        model.addAttribute("dsBenhAn", danhSachBenhAn);
        return "nurse/view-records"; // phải khớp với tên file trong templates/nurse/
    }
    @GetMapping("/record/{id}")
public String hienThiChiTietBenhAn(@PathVariable("id") long id, Model model) {
    for (BenhAn ba : danhSachBenhAn) {
        if (ba.getId() == id) {
            model.addAttribute("benhAn", ba);   
            return "nurse/record-detail"; // trang chi tiết
        }
    }
    return "redirect:/nurse/records";
}

}