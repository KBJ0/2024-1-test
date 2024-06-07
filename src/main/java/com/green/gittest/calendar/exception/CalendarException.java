package com.green.gittest.calendar.exception;

import com.green.gittest.common.model.ResultDto;
import org.apache.ibatis.binding.BindingException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.green.gittest.common.myexception.*;

@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.calendar")
public class CalendarException {
    //1.널|2.유저낫|3.워링벨류|4.런타임|5.데이터인터걸|6.미싱|7.주소타입|8.사인업핸들러|9.메세지타입|

    //1.널
    @ExceptionHandler(NullPointerException.class)
    public ResultDto<String> handleNullPointerException(NullPointerException ex) {
        return ResultDto.resultDto( "VF","모든 필수 항목에 기입해주세요.");
    }
    //2.유저낫
    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST,"NU", "존재하지 않는 유저 입니다.");
    }
//    //3.워링벨류 BindingException
    @ExceptionHandler(BindingException.class)
    public ResultDto<String> handleBindingException(WrongValue ex) {
        return ResultDto.resultDto("뭐가 필요하냐","Ca : 입력된 값이 맞지 않습니다.");
    }
    //4.런타임
    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "DBE","서버에러 입니다.");
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResultDto<String> handlePetNotFoundException(PetNotFoundException ex) {
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "NP","존재하지 않는 반려동물 입니다.");
    }

}