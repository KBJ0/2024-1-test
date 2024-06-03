package com.green.gittest.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostToDoListReq {
    @JsonIgnore
    private long listId;
    @Schema(example = "1", description = "로그인된 유저 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(example = "할 일 내용1234", description = "할 일 내용을 입력하세요", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}
