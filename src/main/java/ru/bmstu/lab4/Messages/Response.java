package main.java.ru.bmstu.lab4.Messages;

import java.util.ArrayList;

public class Response {
    private final String packageID;
    private final ArrayList<String> results;

    public Response(String packageID, ArrayList<String> results) {
        this.packageID = packageID;
        this.results = results;
    }

    public String getPackageID() {
        return packageID;
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
