package com.gurianova.aquariumapi.exception;

public class ServedByOtherMethodException extends RuntimeException {
    public ServedByOtherMethodException() {
        super("Please use the getAllFishes method.");
    }

}
