package com.green.gittest.common;

import com.green.gittest.user.model.SignUpPostReq;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import static com.green.gittest.common.GlobalConst.*;

@Component
@Getter
public class GlobalChecker{

    public void userSignUpChecker(SignUpPostReq p) {
        boolean emailResult = Pattern.matches(p.getEmail(), EMAIL_PATTERN);
        boolean pwResult = Pattern.matches(p.getUpw(), PASSWORD_PATTERN);
        boolean nmResult = Pattern.matches(p.getNickname(), NICKNAME_PATTERN);

        String errorMassage = "발생한 오류 : ";
        if (!emailResult) {
            errorMassage += "\n이메일 형식이 아닙니다.";
        }
        if (!pwResult && PASSWORD_MIN_SIZE <= p.getUpw().length() && p.getUpw().length() <= PASSWORD_MAX_SIZE) {
            errorMassage += "\n비밀번호는 영문, 숫자 포함하여 8~16자리로 입력하세요";
        }
        if (p.getUpw() != p.getCheckPw()) {
            errorMassage += "\n비밀번호를 다시 입력하세요.";
        }
        if (!nmResult) {
            errorMassage += "\n이름을 다시 입력해주세요.";
        }
        if (!errorMassage.equals("발생한 오류 : ")) {
            throw new RuntimeException(errorMassage);
        }
    }
}
