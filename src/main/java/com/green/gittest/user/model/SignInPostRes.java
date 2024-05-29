package com.green.gittest.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInPostRes {
    @Schema(example = "1", description = "유저PK")
    private long userId;
    @Schema(example = "example@naver.com", description = "유저 이메일")
    private String email;
    @Schema(example = "홍길동", description = "유저 이름")
    private String nickname;
    @Schema(example = "randomPicName.jpg", description = "유저 프로필 이미지")
    private String profilePic;
}
