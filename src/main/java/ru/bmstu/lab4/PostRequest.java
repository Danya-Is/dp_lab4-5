package main.java.ru.bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    @JsonProperty("packageID")
    private final int packageID;
    @JsonProperty("jsScript")
    private final String jsScript;
    @JsonProperty("functionName")
    private final String functionName;
    @JsonProperty("tests")
    private final Test[] tests;

    public PostRequest(int packageID, String jsScript, String functionName, Test[] tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public int getPackageID() {
        return packageID;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Test[] getTests() {
        return tests;
    }
}
