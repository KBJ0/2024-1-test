package com.green.gittest.user;



import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.common.GlobalChecker;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import com.green.gittest.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Pattern;
import static com.green.gittest.common.GlobalConst.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;
    private final GlobalChecker check;


    public int postSignUp(MultipartFile pic, SignUpPostReq p) {
        check.userSignUpChecker(p); // 회원가입 시 제한사항 확인함.

        String path = String.format("user/%d", p.getUserId());
        customFileUtils.makeFolders(path); // 해당 유저 파일 저장소 만들기.
        String hashedPw = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashedPw); // 유저 비밀번호 암호화
        if(pic == null) { return mapper.postUser(p);} // 사진 없을경우 현재까지 한 작업만 DB

        String saveFileName = customFileUtils.makeRandomFileName(pic);
        p.setProfilePic(saveFileName);
        String target = String.format("%s/%s", path, saveFileName);
        try {
            customFileUtils.transferTo(pic, target); // 사진 이름 지정 후 파일에 저장
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }

        return mapper.postUser(p);
    }


    public SignInPostRes postSignIn(SignInPostReq p) {
        User user = mapper.getUserById(p.getUid());
        if (user == null) {
            throw new UserNotFoundException("User with UID " + p.getUid() + " not found.");
        } else if (!BCrypt.checkpw(p.getUpw(), user.getUpw())) {
            throw new WrongValue("Invalid password.");
        }

        return SignInPostRes.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profilePic(user.getProfilePic())
                .build();
    }

}