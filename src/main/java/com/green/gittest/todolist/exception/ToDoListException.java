package com.green.gittest.todolist.exception;

import com.green.gittest.common.GlobalExceptionHandler;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.*;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.todolist")
public class ToDoListException extends GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto("NU", "존재하지 않는 유저입니다.");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        String errorMessage = "T : 데이터 무결성 오류입니다(입력하신 값을 확인하세요.)";
        if (ex.getMessage().contains("FOREIGN KEY")) {
            errorMessage = "존재하지 않는 유저입니다.";
        }
        return ResultDto.resultDto("NU", errorMessage);
    }

    @ExceptionHandler(ListNotFoundException.class)
    public ResultDto<String> handleListNotFoundException(ListNotFoundException ex) {
        return ResultDto.resultDto("NL", "존재하지 않는 리스트 입니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("DBE", "서버에러 입니다.");
    }

}
