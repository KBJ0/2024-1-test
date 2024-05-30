package com.green.gittest.todolist;
import com.green.gittest.todolist.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToDoListMapper {
    int postToDoList(PostToDoListReq p);

    List<GetToDoListRes> getToDoListByUserIdForRead(long userId);

    int updateToDoListContent(String content,long listId);
    int updateToDoListIsCompleted(long listId);

    int deleteToDoList(long listId);
}
