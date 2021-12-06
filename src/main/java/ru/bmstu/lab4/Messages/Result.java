package main.java.ru.bmstu.lab4.Messages;

public class Result {
    private final String packageID;
    private final String value;

    public Result(String packageID, String value) {
        this.value = value;
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getValue() {
        return value;
    }
}
