package com.green.gittest.common;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.*;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Order(2)
@RestControllerAdvice
public class GlobalExceptionHandler {
    //1.널|2.유저낫|3.워링벨류|4.런타임|5.데이터인터걸|6.미싱|7.주소타입|8.사인업핸들러|9.메세지타입|

    //1.널
    @ExceptionHandler(NullPointerException.class)
    public ResultDto<String> handleNullPointerException(NullPointerException ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "NullPointerException : 정보를 입력해주세요.");
    }
    //2.유저낫
    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "UserNotFoundException : 존재하지 않는 아이디입니다.");
    }
    //3.워링벨류
    @ExceptionHandler(WrongValue.class)
    public ResultDto<String> handleWrongValueException(WrongValue ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "WrongValue : 입력된 값이 맞지 않습니다.");
    }
    //4.런타임
    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("HttpStatus.INTERNAL_SERVER_ERROR", "RuntimeException : 처리할 수 없는 요청입니다.");
    }
    //5.데이터인터걸
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        String errorMessage = "DataIntegrityViolationException : 해당 부분을 입력해주세요.: " + ex.getRootCause().getMessage() +" (데이터 무결성 위반)";
        // 외래 키 제약 조건 위반 시 특정 메시지 제공
        if (ex.getMessage().contains("FOREIGN KEY")) {
            errorMessage = "FOREIGN KEY ERROR: 존재하는 정보를 입력해주세요. (참조하는 키가 존재하지 않습니다.)";
        }
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", errorMessage);
    }
    //6.미싱
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultDto<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("HttpStatus.INTERNAL_SERVER_ERROR", "MissingServletRequestParameterException :입력하신 정보 일부분이 누락되었습니다.");
    }
    //7.주소타입
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultDto<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("HttpStatus.INTERNAL_SERVER_ERROR", "HttpMediaTypeNotSupportedException : RESTfull 에러. 포스트맨이나 스웨거 바꿔서 사용 or 보내는 값의 타입 확인 하셈.");
    }
    //8.사인업핸들러
    @ExceptionHandler(SignUpException.class)
    public ResultDto<String> handleSignUpException(SignUpException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("HttpStatus.INTERNAL_SERVER_ERROR", "SignUpException : 회원가입 오류임)");
    }
    //9.메세지 못읽음
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultDto<String> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto("HttpStatus.INTERNAL_SERVER_ERROR", "HttpMessageNotReadableException : 입력 양식을 확인해주세요.");
    }

    //10.리스트 낫
    @ExceptionHandler(ListNotFoundException.class)
    public ResultDto<String> handleListNotFoundException(ListNotFoundException ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "ListNotFoundException : 존재하지 않는 리스트입니다.");
    }

    //11.펫 낫
    @ExceptionHandler(PetNotFoundException.class)
    public ResultDto<String> handlePetNotFoundException(PetNotFoundException ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "PetNotFoundException : 존재하지 않는 애완동물입니다.");
    }

    //12. 캘린더 낫
    @ExceptionHandler(CalendarNotFoundException.class)
    public ResultDto<String> handleCalendarNotFoundException(CalendarNotFoundException ex) {
        return ResultDto.resultDto("HttpStatus.BAD_REQUEST", "CalendarNotFoundException : 존재하지 않는 캘린더입니다.");
    }
}