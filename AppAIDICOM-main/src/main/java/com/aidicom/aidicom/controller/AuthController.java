package com.aidicom.aidicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aidicom.aidicom.model.User;
import com.aidicom.aidicom.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hiển thị giao diện Login
    @GetMapping("/login")
    public String showLoginPage() {
        return "register";
    }

    // Hiển thị giao diện Register (nếu muốn tách)
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // ===================== ĐĂNG KÝ Y TÁ =====================
    @PostMapping("/register/nurse")
    public String registerNurse(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam String address,
                                @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        user.setRole("nurse");
        userRepo.save(user);
        return "redirect:/login"; // Quay về login để đăng nhập
    }

    // ===================== ĐĂNG KÝ BÁC SĨ =====================
    @PostMapping("/register/doctor")
    public String registerDoctor(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String address,
                                 @RequestParam String password,
                                 @RequestParam String specialization,
                                 @RequestParam Integer experience) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        user.setRole("doctor");
        user.setSpecialization(specialization);
        user.setExperience(experience);
        userRepo.save(user);
        return "redirect:/login"; // Quay về login để đăng nhập
    }

    // ===================== DASHBOARD =====================
}
