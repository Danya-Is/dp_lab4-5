package main.java.ru.bmstu.lab4.Messages;

public class GetRequest {
    private final String packageId;

    public GetRequest(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageID() {
        return packageId;
    }
}
