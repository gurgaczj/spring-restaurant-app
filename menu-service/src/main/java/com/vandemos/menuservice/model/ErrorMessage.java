package com.vandemos.menuservice.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class ErrorMessage {

    private final LocalDateTime timestamp = LocalDateTime.now();
    @NonNull
    private int status;
    @NonNull
    private String error;
    @NonNull
    private String message;
    @NonNull
    private String path;
}
