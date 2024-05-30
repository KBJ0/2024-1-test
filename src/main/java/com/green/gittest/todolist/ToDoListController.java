package com.green.gittest.todolist;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.todolist.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/todolist")
@Tag(name = "ToDoList", description = "ToDoList CRUD")
public class ToDoListController {
    private final ToDoListService service;

    @PostMapping
    public ResultDto<Long> postToDoList(@RequestBody PostToDoListReq p){
        log.info("p: {}", p);
        if(p == null) throw new NullPointerException();
        service.postToDoList(p);
        return ResultDto.resultDto(HttpStatus.OK,"게시글 작성 완료", p.getListId());
    }

    @GetMapping
    public ResultDto<List<GetToDoListRes>> getToDoList(@RequestParam(name ="userId")Long userId){
        log.info("userId: {}", userId);
        if(userId == null) throw new NullPointerException();
        List<GetToDoListRes> result = service.getToDoList(userId);
        return ResultDto.resultDto(HttpStatus.OK, "일정을 불러옵니다.", result);
    }


    @PatchMapping
    public ResultDto<Integer> updateToDoList(@RequestBody UpdateToDoListReq p){
        log.info("p: {}", p);
        if(p == null) throw new NullPointerException();
        int result = service.updateToDoList(p);
        if(result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 게시물입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK,"게시글 수정완료",result);
    }

    @DeleteMapping
    public ResultDto<Integer> deleteToDoList(@RequestParam(name ="listId") Long listId){
        log.info("listId: {}", listId);
        if(listId == null) throw new NullPointerException();
        int result = service.deleteToDoList(listId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 게시물입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "삭제 완료", result);
    }



}
