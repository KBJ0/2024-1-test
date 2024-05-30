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
    @Schema(example = "홍길동", description = "유저 이름")
    private String nickname;
}
