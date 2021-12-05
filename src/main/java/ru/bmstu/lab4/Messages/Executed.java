package main.java.ru.bmstu.lab4;

public class Executed {
    private final int packageID;
    private final String jsScript;
    private final String functionName;
    private final float expectedResult;
    private final int[] params;

    public Executed(int packageID, String jsScript, String functionName, float expectedResult, int[] params) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public int getPackageID() {
        return packageID;
    }

    public int[] getParams() {
        return params;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }
}
