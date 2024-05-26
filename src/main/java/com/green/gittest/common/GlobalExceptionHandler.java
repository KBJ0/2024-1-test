package com.green.gittest.common;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    public ResultDto<String> handleNullPointerException(NullPointerException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "유저 정보를 입력해주세요.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "존재하지 않는 아이디입니다.");
    }

    @ExceptionHandler(WrongValue.class)
    public ResultDto<String> handleWrongValueException(WrongValue ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "처리할 수 없는 요청입니다.");
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        ex.printStackTrace();
//        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "존재하는 아이디입니다.");
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        String errorMessage = "해당 부분을 입력해주세요.: " + ex.getRootCause().getMessage() +" (데이터 무결성 위반)";
        // 외래 키 제약 조건 위반 시 특정 메시지 제공
        if (ex.getMessage().contains("FOREIGN KEY")) {
            errorMessage = "존재하는 정보를 입력해주세요. (참조하는 키가 존재하지 않습니다.)";
        }
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultDto<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "입력하신 정보 일부분이 누락되었습니다.");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultDto<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "서버 통신 에러. 포스트맨이나 스웨거 바꿔서 사용 or 보내는 값의 타입 확인 하셈. (MissingServletRequestParameterException)");
    }
}