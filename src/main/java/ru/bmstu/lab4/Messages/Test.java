package main.java.ru.bmstu.lab4.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    @JsonProperty("testName")
    private final String testName;
    @JsonProperty("expectedResult")
    private final String expectedResult;
    @JsonProperty("params")
    private final ArrayList<Integer> params;

    public Test(String testName, String expectedResult, ArrayList<Integer> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
