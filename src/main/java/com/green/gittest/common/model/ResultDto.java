package com.green.gittest.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResultDto<T> {

    private HttpStatus statusCode;
    private String resultCode;
    private String resultMsg;
    private T resultData;

    public static <T> ResultDto<T> resultDto(HttpStatus code, String msg, T data) {
        return ResultDto.<T>builder().statusCode(code).resultMsg(msg).resultData(data).build();
    }
    public static <T> ResultDto<T> resultDto(HttpStatus code, String msg) {
        return ResultDto.<T>builder().statusCode(code).resultMsg(msg).build();
    }

    public static <T> ResultDto<T> resultDto(HttpStatus statusCode, String code, String msg) {
        return ResultDto.<T>builder().statusCode(statusCode).resultCode(code).resultMsg(msg).build();
    }
    public static <T> ResultDto<T> resultDto(HttpStatus statusCode, String code, String msg, T data) {
        return ResultDto.<T>builder().statusCode(statusCode).resultCode(code).resultMsg(msg).resultData(data).build();
    }



}
