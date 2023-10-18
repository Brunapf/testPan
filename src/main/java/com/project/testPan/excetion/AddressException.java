package com.project.testPan.excetion;

public class AddressException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AddressException(String message){
        super(message);
    }
    public AddressException(Throwable cause){
        super(cause);
    }

    public AddressException(String message, Throwable cause){
        super(message,cause);
    }
}
