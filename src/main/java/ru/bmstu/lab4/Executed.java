package main.java.ru.bmstu.lab4;

public class Executed {
    private final String jsScript;
    private final String functionName;
    private final int[] params;

    public Executed(String jsScript, String functionName, int[] params) {
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.params = params;
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
