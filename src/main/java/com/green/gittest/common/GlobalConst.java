package com.green.gittest.common;

public abstract class GlobalConst {
    public final static int PASSWORD_MIN_SIZE = 8;
    public final static int PASSWORD_MAX_SIZE = 16;
    public final static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public final static String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$";
    public final static String NICKNAME_PATTERN = "^[가-힣]*$";


}