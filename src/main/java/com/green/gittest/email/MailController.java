package com.green.gittest.email;

import com.green.gittest.email.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.AddressException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/mail")
@Tag(name = "Mail", description = "Mail")
public class MailController {
    private final MailSendService mailService;

    @PostMapping ("/send")
    @Operation(summary = "인증 이메일 전송" , description = "이메일 인증할 이메일을 입력합니다")
    public MailResult<MailSendRes> mailSend(@RequestBody @Valid EmailRequestDto emailDto) throws AddressException  {

        try {
            System.out.println("이메일 인증 요청이 들어옴");
            System.out.println("이메일 인증 이메일 :" + emailDto.getEmail());
           MailSendRes result = mailService.joinEmail(emailDto.getEmail());
            if(result.getEmailCheckCode().equals("이미 가입된 이메일입니다.")){
                return MailResult.<MailSendRes>builder()
                        .code("DE")
                        .message("이미 가입된 이메일입니다.")
                        .data(null).build();
            }
            return MailResult.<MailSendRes>builder()
                    .code("SU")
                    .message("인증번호를 보냈습니다")
                    .data(result).build();

        } catch (Exception e){
            throw new AddressException();
        }
    }

    @PostMapping("/auth_check")
    @Operation(summary = "이메일 인증 확인" , description = "받은 인증번호를 입력합니다")
    public MailResult<MailCheckRes> AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto){
        Boolean Checked= mailService.CheckAuthNum(emailCheckDto.getEmail(),emailCheckDto.getAuthNum());
        MailCheckRes res = new MailCheckRes();
        if(Checked){
            res.setEmailChecked(true);
            return MailResult.<MailCheckRes>builder()
                    .code("SU")
                    .message("인증이 완료되었습니다.")
                    .data(res).build();
        }
        else{
            res.setEmailChecked(false);
            return MailResult.<MailCheckRes>builder()
                    .code("IC")
                    .message("인증번호를 다시 확인해주세요.")
                    .data(null).build();
        }
    }
}
