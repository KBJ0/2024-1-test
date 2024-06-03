package com.green.gittest.todolist.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateToDoListReq {
    @Schema(example = "1", description = "리스트 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long listId;
    @Schema(example = "산책 시키기", description = "수정할 내용을 입력하세요", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}
