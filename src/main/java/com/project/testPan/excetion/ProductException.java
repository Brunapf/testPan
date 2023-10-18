package com.project.testPan.excetion;

public class ProductException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProductException(String message){
        super(message);
    }
    public ProductException(Throwable cause){
        super(cause);
    }

    public ProductException(String message, Throwable cause){
        super(message,cause);
    }
}
