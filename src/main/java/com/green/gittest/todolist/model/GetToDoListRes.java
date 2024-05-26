package com.green.gittest.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class GetToDoListRes {
    private long listId;
    private long userId;
    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2024-10-01", description = "시작 날짜", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2024-10-10", description = "마감 날짜", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date deadlineDate;

    private Boolean isCompleted;
    private Boolean favorite;

    private List<String> contentImages;
}
