package com.green.gittest.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MailResult<T> {
    private String code;
    private String message;
    private T data;
}