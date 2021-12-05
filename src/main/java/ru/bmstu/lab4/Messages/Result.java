package main.java.ru.bmstu.lab4.Messages;

public class Result {
    private final int packageID;
    private final String value;

    public Result(int packageID, String value) {
        this.value = value;
        this.packageID = packageID;
    }

    public int getPackageID() {
        return packageID;
    }

    public String getValue() {
        return value;
    }
}
