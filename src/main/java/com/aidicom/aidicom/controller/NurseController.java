package com.aidicom.aidicom.controller;

import com.aidicom.aidicom.services.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private AIService aiService;

    @GetMapping("/analyze-ai")
    public String showAnalyzePage() {
        return "nurse/analyze_ai";
    }

    @PostMapping("/analyze-ai")
    public String analyzeAI(@RequestParam("image") MultipartFile image, Model model) {
        try {
            String result = aiService.analyze(image);
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("result", "Không nhận diện được nội dung: " + e.getMessage());
        }
        return "nurse/analyze_ai";
    }
}




    




