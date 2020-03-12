package com.vandemos.registerservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {

    public LocalDateTime now;
    public String message;

    public Message(String message) {
        this.now = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
