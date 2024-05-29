package com.green.gittest.common;

import com.green.gittest.common.myexception.SignUpException;
import com.green.gittest.user.model.SignUpPostReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import static com.green.gittest.common.GlobalConst.*;

@Component
@Getter
public class GlobalChecker{
    private StringBuilder errorMassage = new StringBuilder();

    // 이메일 조건 확인
    public void emailChecker(String email){
        boolean emailResult = Pattern.matches(GlobalConst.EMAIL_PATTERN, email);
        if (!emailResult) {
            errorMassage.append("<br>이메일 형식이 아닙니다.");
        }
    }
    // 비번 조건 확인
    public void upwChecker(String upw){
        boolean upwResult = Pattern.matches(GlobalConst.PASSWORD_PATTERN, upw);
        if (!upwResult) {
            errorMassage.append("<br>비밀번호는 영문, 숫자 포함하여 8~16자리로 입력하세요.");
        }
    }
    public void checkPwChecker(String upw, String checkPw){
        if (!upw.equals(checkPw)) {
            errorMassage.append("<br>입력하신 비밀번호와 다릅니다.");
        }
    }

    // 닉넴 조건 확인
    public void nickNameChecker(String nickName){
        boolean nickNameResult = Pattern.matches(GlobalConst.NICKNAME_PATTERN, nickName);
        if (!nickNameResult) {
            errorMassage.append("<br>닉네임은 2글자 이상 6글자 이하의 한글로 구성되어야 합니다.");
        }
    }
    // 회원가입 조건(이메일, 비번, 닉넴) 확인
    public void userSignUpChecker(SignUpPostReq p) {
        errorMassage.setLength(0);  // 초기화
        emailChecker(p.getEmail());
        upwChecker(p.getUpw());
        checkPwChecker(p.getUpw(),p.getCheckPw());
        nickNameChecker(p.getNickname());
        if (!errorMassage.isEmpty()) {
            throw new SignUpException(errorMassage.toString());
        }
    }
}
