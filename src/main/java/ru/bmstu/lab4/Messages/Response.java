package main.java.ru.bmstu.lab4;

import java.util.ArrayList;

public class Response {
    private final int packageID;
    private final ArrayList<String> results;

    public Response(int packageID, ArrayList<String> results) {
        this.packageID = packageID;
        this.results = results;
    }

    public int getPackageID() {
        return packageID;
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
