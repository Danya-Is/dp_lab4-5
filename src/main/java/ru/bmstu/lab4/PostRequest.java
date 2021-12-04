package main.java.ru.bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    @JsonProperty("packageID")
    private int packageID;
    @JsonProperty("jsScript")
    private String jsScript;
    @JsonProperty("functionName")
    private String functionName;
    @JsonProperty("tests")
    private  tests;
}
