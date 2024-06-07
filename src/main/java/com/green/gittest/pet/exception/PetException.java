package com.green.gittest.pet.exception;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.pet")
public class PetException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto("NU", "존재하지 않는 유저 입니다.");
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResultDto<String> handlePetNotFoundException(PetNotFoundException ex) {
        return ResultDto.resultDto("NP", "존재하지 않는 반려동물 입니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("DBE","서버 에러입니다.");
    }

}