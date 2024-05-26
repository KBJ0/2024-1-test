package com.green.gittest.todolist.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PicDto {
    private long feedId;
    @Builder.Default
    private List<String> files = new ArrayList<>();
}
