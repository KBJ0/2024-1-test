package com.green.gittest.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private long userId;
    private String uid;
    private String email;
    private String upw;
    private String nickname;
    private String profilePic;
    private String signUpDate;
    private String profileUpdate;
}
