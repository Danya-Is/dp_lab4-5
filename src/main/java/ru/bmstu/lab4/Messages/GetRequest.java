package main.java.ru.bmstu.lab4.Messages;

public class GetRequest {
    private final int packageId;

    public GetRequest(int packageId) {
        this.packageId = packageId;
    }

    public int getPackageID() {
        return packageId;
    }
}
