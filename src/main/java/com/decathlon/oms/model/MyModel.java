package com.decathlon.oms.model;

public class MyModel {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
