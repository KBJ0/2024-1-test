package com.green.gittest.todolist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PostTodoListPicDto {
    private long listId; //10
    private Long imageId;
    @Builder.Default
    private List<String> fileNames = new ArrayList();
    //"a.jpg", "b.jpg", "c.jpg"
}
