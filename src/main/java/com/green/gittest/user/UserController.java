package com.green.gittest.user;


import com.green.gittest.common.GlobalChecker;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Tag(name = "User 유저", description = "유저 CRUD, sign-in, sign-out")
public class UserController {
    private final UserService service;


    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestBody SignUpPostReq p) {
        return service.postSignUp(p);
    }

    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInPostRes> postSignIn(@RequestBody SignInPostReq p) {
        return service.postSignIn(p);
    }
}