package com.green.gittest.user.exception;

import com.green.gittest.common.GlobalExceptionHandler;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.SignUpException;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.user")
public class UserException{

    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto("NU","로그인에 실패하였습니다");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("DBE", "서버에러 입니다.");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        String errorMessage = "중복된 닉네임 입니다." ;
        if (ex.getMessage().contains("FOREIGN KEY")) {
            errorMessage = "(참조하는 키가 존재하지 않습니다.)";
        }
        return ResultDto.resultDto("DN", errorMessage);// "중복된 닉네임 입니다."
    }

}
