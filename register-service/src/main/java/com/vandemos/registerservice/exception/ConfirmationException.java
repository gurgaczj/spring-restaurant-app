package com.vandemos.registerservice.exception;

public class ConfirmationException extends RuntimeException {

    public ConfirmationException() {
        super();
    }

    public ConfirmationException(String message) {
        super(message);
    }

    public ConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfirmationException(Throwable cause) {
        super(cause);
    }

    protected ConfirmationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
