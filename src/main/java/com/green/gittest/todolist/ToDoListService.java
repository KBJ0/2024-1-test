package com.green.gittest.todolist;

import com.green.gittest.common.CheckMapper;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.ListNotFoundException;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.todolist.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToDoListService {
    private final ToDoListMapper mapper;
    private final CheckMapper checkMapper;

    public ResultDto<PostToDoListRes> postToDoList(PostToDoListReq p) {
        if(p.getContent() == null || p.getContent().isEmpty()) throw new NullPointerException();
        mapper.postToDoList(p);
        PostToDoListRes result = new PostToDoListRes();
        result.setListId(p.getListId());
        String msg = "투두리스트 등록이 정상적으로 완료되었습니다.";
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg, result);
    }


    public ResultDto<List<GetToDoListRes>> getToDoList(Long userId) {
        if(checkMapper.getUserId(userId) == null ) throw new UserNotFoundException();
        List<GetToDoListRes> list = mapper.getToDoListByUserIdForRead(userId);
        String msg = "투두리스트 일정을 정상적으로 불러왔습니다.";
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg, list);
    }

    public ResultDto<Integer> updateToDoList(UpdateToDoListReq p) {
        if(p.getContent() == null || p.getContent().isEmpty()) throw new NullPointerException();
        if(checkMapper.getUserId(p.getUserId()) == null ) throw new UserNotFoundException();
        int result = mapper.updateToDoListContent(p);
        String msg = "투두리스트 내용 수정이 정상적으로 완료되었습니다.";
        if(result == 0) throw new ListNotFoundException();
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg);
    }

    public ResultDto<Integer> updateToDoListIsCompleted(long listId) {
        if(checkMapper.getListId(listId) == 0) throw new NullPointerException();
        int result = checkMapper.getToDoListIsCompleted(listId);
        mapper.updateToDoListIsCompleted(listId);
        if(result == 1)  return ResultDto.resultDto( "SU", "투두리스트 완료여부를 체크삭제하였습니다.", 1-result);
        return ResultDto.resultDto( "SU", "투두리스트 완료여부를 체크하였습니다.", 1-result);
    }


    public ResultDto<Integer> deleteToDoList(Long listId) {
        int result = mapper.deleteToDoList(listId);
        String msg = "투두리스트 삭제가 정상적으로 완료되었습니다.";
        if(result == 0) throw new ListNotFoundException();
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg);
    }

    public ResultDto<Integer> deleteAllTodoList(Long userId){
        if(userId == null) throw new NullPointerException();
        int result = mapper.deleteAllTodoList(userId);
        String msg = "완료한 일정 전체 삭제가 완료되었습니다.";
        if(result == 0) throw new ListNotFoundException("존재하지 않는 유저입니다.");
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg);
    }

}
