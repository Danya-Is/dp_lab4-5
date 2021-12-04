package main.java.ru.bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    @JsonProperty("testName")
    private final String testName;
    @JsonProperty("expectedResult")
    private final float expectedResult;
    @JsonProperty("params")
    private final int[] params;

    public Test(String testName, float expectedResult, int[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
}
