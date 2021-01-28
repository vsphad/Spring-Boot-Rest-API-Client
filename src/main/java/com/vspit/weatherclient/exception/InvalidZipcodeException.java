package com.vspit.weatherclient.exception;

public class InvalidZipcodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidZipcodeException(String errorMessage) {
        super(errorMessage);
    }

}
