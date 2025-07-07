package com.aidicom.aidicom.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class AIService {

    private final String GEMINI_API_KEY = "AIzaSyAz1AsGR3hUP-cZNyeTVCK5QplptltcCiM"; // <-- Đổi key của bạn
    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=" + GEMINI_API_KEY;

    public String analyze(MultipartFile imageFile) throws Exception {
        byte[] imageBytes = imageFile.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // Tạo JSON payload
        String jsonPayload = """
        {
          "contents": [
            {
              "parts": [
                {
                  "inlineData": {
                    "mimeType": "%s",
                    "data": "%s"
                  }
                },
                {
                  "text": "Bạn hãy chẩn đoán chi tiết ảnh y tế này để hỗ trợ bác sĩ và y tá."
                }
              ]
            }
          ]
        }
        """.formatted(imageFile.getContentType(), base64Image);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(jsonPayload, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(GEMINI_API_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Lỗi gọi Gemini API: " + response.body().string());
            }
            String responseBody = response.body().string();

            // Parse JSON để lấy text trả lời
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            String textResult = root
                    .path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text")
                    .asText();

            return textResult;
        }
    }
}
