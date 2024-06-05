package com.green.gittest.user;

import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import com.green.gittest.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserMapper mapper;

    public int postSignUp(SignUpPostReq p) {
        String hashedPw = BCrypt.hashpw(p.getPassword(), BCrypt.gensalt());
        p.setPassword(hashedPw); // 유저 비밀번호 암호화
        return mapper.postUser(p);
    }

    public SignInPostRes postSignIn(SignInPostReq p) {
        User user = mapper.getUserByEmail(p.getEmail()); // 널포인트 뜨는가?
        if (user == null || !BCrypt.checkpw(p.getPassword(), user.getPassword())) {
            throw new UserNotFoundException();}

        return SignInPostRes.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }
}