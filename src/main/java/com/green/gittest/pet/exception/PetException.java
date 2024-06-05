package com.green.gittest.pet.exception;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.SignUpException;
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

@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.pet")
public class PetException {
    //1.널|2.유저낫|3.워링벨류|4.런타임|5.데이터인터걸|6.미싱|7.주소타입|8.사인업핸들러|9.메세지타입|

    //1.널
    @ExceptionHandler(NullPointerException.class)
    public ResultDto<String> handleNullPointerException(NullPointerException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST,"VF","모든 필수 항목에 기입해주세요.");
    }
    //2.유저낫
    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST,"NU", "존재하지 않는 유저 입니다.");
    }
//    //3.워링벨류
//    @ExceptionHandler(WrongValue.class)
//    public ResultDto<String> handleWrongValueException(WrongValue ex) {
//        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "P : 입력된 값이 맞지 않습니다.");
//    }
    //4.런타임
    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "DBE","서버에러 입니다.");
    }
//    //5.데이터인터걸
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        ex.printStackTrace();
//        String errorMessage = "P : 해당 부분을 입력해주세요.: " + ex.getRootCause().getMessage() +" (데이터 무결성 위반)";
//        // 외래 키 제약 조건 위반 시 특정 메시지 제공
//        if (ex.getMessage().contains("FOREIGN KEY")) {
//            errorMessage = "P FOREIGN: 존재하는 정보를 입력해주세요. (참조하는 키가 존재하지 않습니다.)";
//        }
//        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, errorMessage);
//    }
//    //6.미싱
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResultDto<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
//        ex.printStackTrace();
//        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "P :입력하신 정보 일부분이 누락되었습니다.");
//    }
//    //7.주소타입
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResultDto<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
//        ex.printStackTrace();
//        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "P : RESTfull 에러. 포스트맨이나 스웨거 바꿔서 사용 or 보내는 값의 타입 확인 하셈.");
//    }
//    //8.사인업핸들러
//    @ExceptionHandler(SignUpException.class)
//    public ResultDto<String> handleSignUpException(SignUpException ex) {
//        ex.printStackTrace();
//        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "P : 회원가입 오류임)");
//    }
//    //9.메새지 못읽음
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResultDto<String> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//        ex.printStackTrace();
//        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "P : 입력 양식을 확인해주세요.");
//    }

}