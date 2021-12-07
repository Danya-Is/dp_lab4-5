package main.java.ru.bmstu.lab4.Messages;

import java.util.ArrayList;
import java.util.Map;

public class Response {
    private final String packageID;
    private final Map<String, String> results;

    public Response(String packageID, Map<String, String> results) {
        this.packageID = packageID;
        this.results = results;
    }

    public String getPackageID() {
        return packageID;
    }

    public Map<String, String> getResults() {
        return results;
    }
}
