package com.picarro.ai;

import java.nio.file.Files;
import java.nio.file.Path;

public class PRDParser {

    public static String readPRD() {

        try {
            return Files.readString(Path.of("test-data/prd.md"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read PRD file", e);
        }
    }
}
