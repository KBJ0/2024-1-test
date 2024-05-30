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
    private String content;
    private int isCompleted;
    private String createdAt;
    private String updatedAt;
}
