package main.java.ru.bmstu.lab4.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostRequest {
    @JsonProperty("packageId")
    private final String packageID;
    @JsonProperty("jsScript")
    private final String jsScript;
    @JsonProperty("functionName")
    private final String functionName;
    @JsonProperty("tests")
    private final ArrayList<Test> tests;

    public PostRequest(String packageID, String jsScript, String functionName, ArrayList<Test> tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
