package com.picarro.ai;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestCaseGenerator {

    public static void generateTests() throws IOException {

        String prdText = PRDParser.readPRD();

        String prompt = """
Convert PRD into Selenium test cases.

IMPORTANT:
- Use REAL locators from DOM
- For login button use:
  xpath=//button[@type='submit']
- For username:
  name=username
- For password:
  name=password

Return ONLY JSON.

Format:
{
 "testCases":[
  {
   "name":"",
   "steps":[
    {
      "action":"",
      "locator":"",
      "value":"",
      "url":""
    }
   ]
  }
 ]
}

Actions allowed:
navigate
click
type
assert_url
assert_element

PRD:
""" + prdText;

        String response = AIClient.callLLM(prompt);

        // 🔥 Extract ONLY JSON part
        int start = response.indexOf("{");
        int end = response.lastIndexOf("}");

        if (start == -1 || end == -1) {
            throw new RuntimeException("Invalid AI response: " + response);
        }

        String cleanJson = response.substring(start, end + 1);

        // ✅ Save clean JSON ONLY (correct path)
        String path = System.getProperty("user.dir") + "/../test-data/generated-tests.json";

        FileWriter writer = new FileWriter(path);
        writer.write(cleanJson);
        writer.close();

        System.out.println("✅ Generated test cases saved to: " + path);
    }
}