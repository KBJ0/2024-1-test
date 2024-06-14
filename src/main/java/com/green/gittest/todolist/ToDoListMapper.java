package com.green.gittest.todolist;
import com.green.gittest.todolist.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToDoListMapper {
    int postToDoList(PostToDoListReq p);
    List<GetToDoListRes> getToDoListByUserIdForRead(long userId);
    List<GetToDoListRes> getUpcomingToDoList(long userId);
    int updateToDoListContent(UpdateToDoListReq p);
    int updateToDoListIsCompleted(long listId);
    int deleteToDoList(Long listId);
    int deleteAllTodoList(Long userId);
}
