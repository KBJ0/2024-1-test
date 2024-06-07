package com.green.gittest.todolist;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.todolist.model.*;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "일정 등록" , description = "등록할 일정을 작성합니다")
    public ResultDto<PostToDoListRes> postToDoList(@RequestBody PostToDoListReq p){
        return service.postToDoList(p);
    }

    @GetMapping
    @Operation(summary = "일정 불러오기" , description = "완료된 일정까지 모두 불러옵니다.")
    public ResultDto<List<GetToDoListRes>> getToDoList(@RequestParam(name ="user_id")Long userId){
        return service.getToDoList(userId);
    }

    @GetMapping("upcoming")
    @Operation(summary = "다가오는 일정 불러오기" , description = "다가오는 일정 중 상위 3개를 불러옵니다.")
    public ResultDto<List<GetToDoListRes>> getUpcomingToDoList(@RequestParam(name ="user_id")Long userId){
        return service.getUpcomingToDoList(userId);
    }

    @PatchMapping
    @Operation(summary = "일정 수정" , description = "listId는 일정의 pk")
    public ResultDto<Integer> updateToDoList(@RequestBody UpdateToDoListReq p){
        return service.updateToDoList(p);
    }

    @PatchMapping("is-completed")
    @Operation(summary = "일정 수정" , description = "listId는 일정의 pk")
    public ResultDto<Integer> updateToDoListIsCompleted(@RequestParam(name = "list_id") long listId){
        return service.updateToDoListIsCompleted(listId);
    }

    @DeleteMapping
    @Operation(summary = "일정 삭제" , description = "listId는 일정의 pk")
    public ResultDto<Integer> deleteToDoList(@RequestParam(name ="list_id") Long listId){
        return service.deleteToDoList(listId);
    }

    @DeleteMapping("all-delete")
    @Operation(summary = "완료된 일정 전체 삭제" , description = "userId는 로그인된 유저의 pk")
    public ResultDto<Integer> deleteAllTodoList(@RequestParam(name ="user_id") Long userId){
        return service.deleteAllTodoList(userId);
    }


}
