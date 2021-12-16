package ru.bmstu.lab5;

public class Response {
    private String url;
    private float averageResponseTime;

    public Response(String url, float averageResponseTime) {
        this.url = url;
        this.averageResponseTime = averageResponseTime;
    }

    public float getAverageResponseTime() {
        return averageResponseTime;
    }

    public String getUrl() {
        return url;
    }
}
