package com.mbm.clientservice.exception;

public class SiretAlreadyUsedException extends RuntimeException {
    public SiretAlreadyUsedException(String message) {
        super(message);
    }
}
