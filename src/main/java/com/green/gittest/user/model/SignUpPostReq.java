package com.green.gittest.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpPostReq {
    @JsonIgnore
    private long userId;

    @Schema(example = "example@naver.com", description = "이메일 형식으로 작성해주세요.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(example = "abcd1234!@#$", description = "비밀번호는 영문, 숫자 포함하여 8~16자리로 입력하세요.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(example = "abcd1234!@#$", description = "위와 동일한 비밀번호를 입력해주세요.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String checkPassword;
    @Schema(example = "홍길동", description = "이름은 2글자 이상 6글자 이하의 한글로 작성해주세요.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    SignUpPostReq(String email, String password, String checkPassword, String nickname){
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
        this.nickname = nickname;
        // 빈값 체크
        // null, "", " "
        if(email == null){this.email = "";}
        if(password == null){this.email = "";}
        if(checkPassword == null){this.email = "";}
        if(nickname == null){this.email = "";}
    }

}

// 사진 있을 경우 사용
//    @JsonIgnore
//    private String profilePic;
