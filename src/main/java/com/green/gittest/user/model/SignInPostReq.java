package com.green.gittest.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignInPostReq {
    @Schema(example = "example@naver.com", description = "유저 이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(example = "abcd1234!@#$", description = "유저 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
