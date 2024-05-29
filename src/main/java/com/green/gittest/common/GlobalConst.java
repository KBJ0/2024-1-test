package com.green.gittest.common;

public abstract class GlobalConst {
    public final static int PASSWORD_MIN_SIZE = 8;
    public final static int PASSWORD_MAX_SIZE = 16;
    public final static int NICKNAME_MIN_SIZE = 2;
    public final static int NICKNAME_MAX_SIZE = 6;


    public final static String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public final static String PASSWORD_PATTERN =
            String.format("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%%^&*(),.?\":{}|<>]).{%d,%d}$", PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE);

    public final static String NICKNAME_PATTERN =
            String.format("^[가-힣]{%d,%d}$",NICKNAME_MIN_SIZE,NICKNAME_MAX_SIZE);


}