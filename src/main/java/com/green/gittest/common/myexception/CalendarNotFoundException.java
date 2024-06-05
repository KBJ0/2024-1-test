package com.green.gittest.common.myexception;

public class CalendarNotFoundException extends RuntimeException {
    public CalendarNotFoundException() {super();}
    public CalendarNotFoundException(String message) {
        super(message);
    }
    public CalendarNotFoundException(String message, Throwable cause) {super(message, cause);}
    public CalendarNotFoundException(Throwable cause) {super(cause);}
}