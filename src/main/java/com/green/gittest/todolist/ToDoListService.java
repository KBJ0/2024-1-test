package com.green.gittest.todolist;

import com.green.gittest.common.myexception.SignUpException;
import com.green.gittest.todolist.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToDoListService {
    private final ToDoListMapper mapper;

    public int postToDoList(PostToDoListReq p) {
        return mapper.postToDoList(p);
    }

    public List<GetToDoListRes> getToDoList(Long userId) {
        return mapper.getToDoListByUserIdForRead(userId);
    }

    public int updateToDoList(UpdateToDoListReq p) {
        return mapper.updateToDoListContent(p);
    }

    public int deleteToDoList(Long listId) {
        return mapper.deleteToDoList(listId);
    }

    public int deleteAllTodoList(Long userId){
        return mapper.deleteAllTodoList(userId);
    }

}
