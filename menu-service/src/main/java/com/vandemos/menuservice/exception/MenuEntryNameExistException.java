package com.vandemos.menuservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MenuEntryNameExistException extends RuntimeException {
    public MenuEntryNameExistException(String message) {
        super(message);
    }
}
