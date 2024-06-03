package com.green.gittest.todolist.iscompleted.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetIsCompletedListRes {
    private long listId;
    private String content;
    private int isCompleted;
    private String createdAt;
}
