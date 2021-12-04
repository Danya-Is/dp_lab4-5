package main.java.ru.bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostRequest {
    @JsonFormat("packageID")
    private int packageID;
}
