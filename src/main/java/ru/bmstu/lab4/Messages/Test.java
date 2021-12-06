package main.java.ru.bmstu.lab4.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    @JsonProperty("testName")
    private final String testName;
    @JsonProperty("expectedResult")
    private final String expectedResult;
    @JsonProperty("params")
    private final ArrayList<Object> params;

    public Test(@JsonProperty("testName") String testName,
                @JsonProperty("expectedResult") String expectedResult,
                @JsonProperty("params") ArrayList<Object> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public ArrayList<Object> getParams() {
        return params;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
