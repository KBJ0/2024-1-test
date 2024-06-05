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
    public ResultDto<Long> postToDoList(@RequestBody PostToDoListReq p){
        log.info("p: {}", p);
        if(p.getContent() == null || p.getContent().isEmpty()) throw new NullPointerException();
        service.postToDoList(p);
        return ResultDto.resultDto(HttpStatus.OK,"일정 작성 완료", p.getListId());
    }

    @GetMapping
    @Operation(summary = "일정 불러오기" , description = "완료된 일정까지 모두 불러옵니다.")
    public ResultDto<List<GetToDoListRes>> getToDoList(@RequestParam(name ="userId")Long userId){
        log.info("userId: {}", userId);
        if(userId == null) throw new NullPointerException();
        List<GetToDoListRes> result = service.getToDoList(userId);
        return ResultDto.resultDto(HttpStatus.OK, "일정을 불러옵니다.", result);
    }

    @PatchMapping
    @Operation(summary = "일정 수정" , description = "listId는 일정의 pk")
    public ResultDto<Integer> updateToDoList(@RequestBody UpdateToDoListReq p){
        log.info("p: {}", p);
        if(p.getContent() == null || p.getContent().isEmpty()) throw new NullPointerException();
        int result = service.updateToDoList(p);
        if(result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 일정입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK,"일정 수정 완료",result);
    }

    @DeleteMapping
    @Operation(summary = "일정 삭제" , description = "listId는 일정의 pk")
    public ResultDto<Integer> deleteToDoList(@RequestParam(name ="listId") Long listId){
        log.info("listId: {}", listId);
        if(listId == null) throw new NullPointerException();
        int result = service.deleteToDoList(listId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 일정입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "일정 삭제 완료", result);
    }

    @DeleteMapping("all-delete")
    @Operation(summary = "완료된 일정 전체 삭제" , description = "userId는 로그인된 유저의 pk")
    public ResultDto<Integer> deleteAllTodoList(@RequestParam(name ="userId") Long userId){
        log.info("userId: {}", userId);
        if(userId == null) throw new NullPointerException();
        int result = service.deleteAllTodoList(userId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "삭제할 게시물이 존재하지 않습니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "완료 일정 전체 삭제 완료", result);
    }

}
