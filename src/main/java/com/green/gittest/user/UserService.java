package com.green.gittest.user;

import com.green.gittest.common.CheckMapper;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.NicknameException;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import com.green.gittest.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserMapper mapper;
    private final CheckMapper checkMapper;

    @Transactional
    public ResultDto<Integer> postSignUp(SignUpPostReq p) {
        if(checkMapper.checkNickname(p.getNickname()) != null) throw new NicknameException();
        String hashedPw = BCrypt.hashpw(p.getPassword(), BCrypt.gensalt());
        p.setPassword(hashedPw);
        mapper.postUser(p);
        return ResultDto.resultDto("SU", "회원가입 성공");
    }

    public ResultDto<SignInPostRes> postSignIn(SignInPostReq p) {
        User user = mapper.getUserByEmail(p.getEmail());
        if (user == null || !BCrypt.checkpw(p.getPassword(), user.getPassword())) {
            throw new UserNotFoundException();}

        return ResultDto.resultDto("SU", "로그인 성공"
                , SignInPostRes.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build());
    }
}