package com.main.exception;

public class CustomerExistsException extends Exception{
    public CustomerExistsException() {
        super();
    }

    public CustomerExistsException(String message) {
        super(message);
    }

    public CustomerExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerExistsException(Throwable cause) {
        super(cause);
    }

    protected CustomerExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
