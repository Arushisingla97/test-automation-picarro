package com.picarro.api.ai;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIResponseAnalyzer {

    public static void validateResponse(String responseBody) {

        try {

            URL url = new URL("http://localhost:11434/api/generate");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String prompt = """
Validate this API response.

Check:
1. Is it valid country data?
2. Does it contain meaningful fields like name, capital, region?
3. Is structure correct (array/object)?

Return ONLY:
VALID or INVALID

Response:
""" + responseBody.substring(0, Math.min(1000, responseBody.length()));

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

            JSONObject res = new JSONObject(response.toString());
            String result = res.getString("response");

            System.out.println("🤖 AI Validation Result: " + result);

            if (!result.toUpperCase().contains("VALID")) {
                throw new RuntimeException("AI validation failed");
            }

        } catch (Exception e) {
            throw new RuntimeException("AI validation error", e);
        }
    }
}