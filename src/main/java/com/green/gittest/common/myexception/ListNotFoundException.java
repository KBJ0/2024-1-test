package com.green.gittest.common.myexception;

public class ListNotFoundException  extends RuntimeException {
    public ListNotFoundException() {super();}
    public ListNotFoundException(String message) {
        super(message);
    }
    public ListNotFoundException(String message, Throwable cause) {super(message, cause);}
    public ListNotFoundException(Throwable cause) {super(cause);}
}
