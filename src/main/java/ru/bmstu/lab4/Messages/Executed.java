package main.java.ru.bmstu.lab4.Messages;

import java.util.ArrayList;

public class Executed {
    private final String packageID;
    private final String jsScript;
    private final String functionName;
    private final String expectedResult;
    private final ArrayList<Object> params;

    public Executed(String packageID, String jsScript, String functionName, String expectedResult, ArrayList<Object> params) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getPackageID() {
        return packageID;
    }

    public ArrayList<Object> getParams() {
        return params;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }
}
