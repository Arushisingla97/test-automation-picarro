package com.picarro.ui.ai;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AILocatorHealer {

    public static String healLocator(String brokenLocator, String dom) {

        try {

            URL url = new URL("http://localhost:11434/api/generate");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String prompt = """
Fix this Selenium locator.

Return ONLY one locator.

Format strictly:
id=value OR name=value OR xpath=value OR css=value

NO explanation.

Broken locator:
""" + brokenLocator;

            JSONObject json = new JSONObject();
            json.put("model", "llama3");
            json.put("prompt", prompt);
            json.put("stream", false);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes());
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            System.out.println("🔴 AI RAW RESPONSE: " + response);

            JSONObject responseJson = new JSONObject(response.toString());
            String raw = responseJson.getString("response").trim();

            // =========================
            // 🔥 FIXED CLEANING (NO SPACE SPLIT)
            // =========================

            String result = raw
                    .replace("By.xpath:", "xpath=")
                    .replace("By.id:", "id=")
                    .replace("By.name:", "name=")
                    .replace("By.cssSelector:", "css=")
                    .replace("\n", "")
                    .trim();

            // Extract locator safely
            if (result.startsWith("xpath=")) {
                // KEEP FULL XPATH (DO NOT SPLIT)
            } else if (result.startsWith("id=")
                    || result.startsWith("name=")
                    || result.startsWith("css=")) {

                // Safe for these
                result = result.split("\\s")[0];
            } else {
                throw new RuntimeException("Invalid AI locator: " + result);
            }

            // Final validation
            if (result.endsWith("=")) {
                throw new RuntimeException("Empty locator generated: " + result);
            }

            System.out.println("🤖 Final Clean Locator: " + result);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("AI request failed", e);
        }
    }
}