package com.green.gittest.common;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.user.model.SignUpPostReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;
import static com.green.gittest.common.GlobalConst.*;

@Component
@Getter
public class GlobalChecker{
    private StringBuilder errorMassage = new StringBuilder();
    private int errorNumber;

    // 이메일 조건 확인
    public void emailChecker(String email){
        boolean emailResult = Pattern.matches(GlobalConst.EMAIL_PATTERN, email);
        if(email.isEmpty()){
            errorMassage.append("<br>이메일을 입력해주세요.");
            errorNumber += 1;
        } else if (!emailResult) {
            errorMassage.append("<br>이메일 형식이 아닙니다.");
            errorNumber += 1;
        }
    }
    // 비번 조건 확인
    public void upwChecker(String password){
        boolean passwordResult = Pattern.matches(GlobalConst.PASSWORD_PATTERN, password);
        if(password.isEmpty()){
            errorMassage.append("<br>비밀번호를 입력해주세요.");
            errorNumber += 2;
        }else if (!passwordResult) {
            errorMassage.append("<br>비밀번호는 영문, 숫자, 특수문자 포함하여 8~16자리로 입력하세요.");
            errorNumber += 2;
        }
    }
    // 비번 한번더 확인
    public void checkPwChecker(String password, String checkPassword){
        if(checkPassword.isEmpty()){
            errorMassage.append("<br>입력한 비밀번호와 같은 값을 입력해주세요.");
            errorNumber += 4;
        }else if (!password.equals(checkPassword)) {
            errorMassage.append("<br>입력하신 비밀번호와 다릅니다.");
            errorNumber += 4;
        }
    }

    // 닉넴 조건 확인
    public void nickNameChecker(String nickName){
        boolean nickNameResult = Pattern.matches(GlobalConst.NICKNAME_PATTERN, nickName);
        if(nickName.isEmpty()){
            errorMassage.append("<br>닉네임을 입력해주세요.");
            errorNumber += 8;
        }else if (!nickNameResult) {
            errorMassage.append("<br>닉네임은 2글자 이상 6글자 이하의 한글로 구성되어야 합니다.");
            errorNumber += 8;
        }
    }
    // 회원가입 조건(이메일, 비번, 닉넴) 확인
    public void userSignUpChecker(SignUpPostReq p) {
            errorMassage.setLength(0);  // 초기화
            errorNumber = 0;
            emailChecker(p.getEmail());
            upwChecker(p.getPassword());
            checkPwChecker(p.getPassword(),p.getCheckPassword());
            nickNameChecker(p.getNickname());
    }
}
