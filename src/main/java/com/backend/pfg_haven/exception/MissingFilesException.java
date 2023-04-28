package com.backend.pfg_haven.exception;

public class MissingFilesException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MissingFilesException(String message) {
        super(message);
    }

    public MissingFilesException(String message, Throwable cause) {
        super(message, cause);
    }

}
