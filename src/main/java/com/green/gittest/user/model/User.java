package com.green.gittest.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private long userId;
    private String email;
    private String password;
    private String nickname;
    private String createdAt;
    private String updatedAt;
}
