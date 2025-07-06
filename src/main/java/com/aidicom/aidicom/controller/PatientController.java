package com.aidicom.aidicom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nurse")
public class PatientController {

    @GetMapping("/patient-records")
    public String patientRecords() {
        return "patient-records"; // Trả về tên file HTML hoặc Thymeleaf tương ứng
    }
}
