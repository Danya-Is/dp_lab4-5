package main.java.ru.bmstu.lab4.Messages;

public class Result {
    private final String packageID;
    private final String testName;
    private final String expectedResult;
    private final String value;

    public Result(String packageID, String testName, String expectedResult, String value) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.value = value;
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getValue() {
        return value;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
