package com.picarro.ai;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIClient {

    public static String callLLM(String prompt) {

        try {

            URL url = new URL("http://localhost:11434/api/generate");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // 🔥 FIX: Proper escaping
            String safePrompt = prompt
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "");

            String requestBody = "{\n" +
                    "  \"model\": \"llama3\",\n" +
                    "  \"prompt\": \"" + safePrompt + "\",\n" +
                    "  \"stream\": false\n" +
                    "}";

            // Debug log (VERY useful)
            System.out.println("REQUEST BODY:\n" + requestBody);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes());
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (Exception e) {
            throw new RuntimeException("AI request failed", e);
        }
    }
}