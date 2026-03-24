package com.picarro.ai;

import java.util.List;

public class TestCaseModel {

    private String name;
    private List<String> steps;
    private String expectedResult;

    public String getName() {
        return name;
    }

    public List<String> getSteps() {
        return steps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
