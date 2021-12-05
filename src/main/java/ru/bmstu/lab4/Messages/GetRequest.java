package main.java.ru.bmstu.lab4.Messages;

public class GetRequest {
    private final int packageID;

    public GetRequest(int packageID) {
        this.packageID = packageID;
    }

    public int getPackageID() {
        return packageID;
    }
}
