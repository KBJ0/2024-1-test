package com.green.gittest.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto<T> {

    private String code;
    private String message;
    private T data;

    public static <T> ResultDto<T> resultDto(String code, String msg) {
        return ResultDto.<T>builder().code(code).message(msg).build();
    }

    public static <T> ResultDto<T> resultDto(String code, String msg, T data) {
        return ResultDto.<T>builder().code(code).message(msg).data(data).build();
    }



}
