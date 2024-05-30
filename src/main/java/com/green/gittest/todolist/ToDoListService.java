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
        List<GetToDoListRes> res = mapper.getToDoListByUserIdForRead(userId);
        if(res == null || res.isEmpty()){return null;}
        return res;
    }

    public int updateToDoList(UpdateToDoListReq p) {
       if (p.getIsCompleted() == 1){
           return mapper.updateToDoListIsCompleted(p.getListId());
       }
        return mapper.updateToDoListContent(p.getContent(),p.getListId());
    }

    public int deleteToDoList(Long listId) {
        return mapper.deleteToDoList(listId);
    }



}
