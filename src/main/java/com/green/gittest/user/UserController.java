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
    private final GlobalChecker check;


    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestBody SignUpPostReq p) {
        log.info("p: {}", p);

        service.postSignUp(p);
        return ResultDto.resultDto(HttpStatus.OK,"SU","회원가입 성공");
    }
//if(p == null) throw new NullPointerException(); // 값이 없으면 바로 결과를 줌.(서비스 미실행)

//      check.userSignUpChecker(p); // 회원가입 시 제한사항 확인함.
//      if(check.getErrorNumber() > 0){
//      return ResultDto.resultDto(HttpStatus.BAD_REQUEST,""+check.getErrorMassage(),check.getErrorNumber());}
//      2^0:이메일, 2^1:비밀번호, 2^2:비밀번호 확인, 2^3:닉네임 : %해서 홀수면 해당 에러확인 가능.


    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInPostRes> postSignIn(@RequestBody SignInPostReq p) {
        log.info("p: {}", p);
        SignInPostRes result = service.postSignIn(p);
        return ResultDto.resultDto(HttpStatus.OK,"SU","로그인 성공",result);
    }
}
// if(p == null) throw new NullPointerException(); // 값이 없으면 바로 결과를 줌.(서비스 미실행)


/* 로그인 프로필 사진 있는 버전.
  @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic
            , @RequestPart SignUpPostReq p) {
        log.info("pic: {}", pic);
        log.info("p: {}", p);
        if(p == null) throw new NullPointerException(); // 값이 없으면 바로 결과를 줌.(서비스 미실행)
        int result = service.postSignUp(pic, p);
        return ResultDto.resultDto(HttpStatus.OK,"회원가입 성공",result);
    }
 */