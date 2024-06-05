package com.green.gittest.email.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {
    @Email
    /*
    이 어노테이션은 해당 필드가 유효한 이메일 형식인지 검증합니다.
    이메일 형식 검증 규칙:
    @ 기호를 포함해야 합니다.
    @ 기호를 기준으로 이메일 주소를 이루는 로컬 파트와 도메인 파트가 존재해야 합니다.
    도메인 파트는 최소 하나의 점(.)을 포함하고, 점 뒤에 최소 두 개의 알파벳을 가져야 합니다.
    예제:
    유효한 이메일: example@example.com
    유효하지 않은 이메일: example.com (도메인 파트가 없음)
    */
    @NotBlank(message = "이메일을 입력해 주세요")
    /*
    이 어노테이션은 해당 필드가 비어 있지 않은지 검증합니다. 즉, null이 아니고 빈 문자열이 아닌 경우에만 유효합니다.
    message 속성을 통해 유효성 검증 실패 시 사용자에게 보여줄 에러 메시지를 지정할 수 있습니다.
    이 경우, 이메일 필드가 비어 있을 때 "이메일을 입력해 주세요"라는 메시지가 표시됩니다.
    예제:
    유효한 값: example@example.com
    유효하지 않은 값: "" (빈 문자열), null
     */
    private String email;


}
