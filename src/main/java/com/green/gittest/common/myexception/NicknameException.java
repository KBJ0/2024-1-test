package com.green.gittest.common.myexception;

public class NicknameException extends RuntimeException {
    public NicknameException() {super();}
    public NicknameException(String message) {
        super(message);
    }
    public NicknameException(String message, Throwable cause) {super(message, cause);}
    public NicknameException(Throwable cause) {super(cause);}
}