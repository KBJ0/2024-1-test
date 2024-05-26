package com.green.gittest.todolist;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.todolist.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/todolist")
@Tag(name = "ToDoList", description = "ToDoList CRUD")
public class ToDoListController {
    private final ToDoListService service;

 // API 명세서 Response 값을 int가 아닌거 같음. 뭐로 바꿔야하는지 코드실행하고 확인해봐야함.
 // listId랑 userId 타입을 long or int 하나로 통일하는게 좋아보임.
    @PostMapping
    public ResultDto<PostToDoListRes> postToDoList(@RequestPart(required = false) List<MultipartFile> pics
            , @RequestPart PostToDoListReq p){
        log.info("pics: {}", pics); log.info("p: {}", p);

        if(p == null) throw new NullPointerException();
        PostToDoListRes result = service.postToDoList(pics, p);
        return ResultDto.resultDto(HttpStatus.OK,"게시글 작성",result);
    }


    @GetMapping
    public ResultDto<List<GetToDoListRes>> getToDoList(@RequestParam(name ="signedUserId") Integer userId){
        log.info("userId: {}", userId);
        List<GetToDoListRes> result = service.getToDoList(userId);
        return ResultDto.resultDto(HttpStatus.OK, "일정을 불러옵니다.", result);
    }

    // 사진을 패치할 경우 어떻게 처리할 것인가?
    // 1. 기존 사진을 지우고 업로드한 사진만 넣는다.
    // 2. 기존 사진을 유지하고 업로드한 사진을 추가한다.
    // 3. 기존 사진을 지우는 기능을 만들고, 해당 코드는 2번을 따라간다.
    @PatchMapping
    public ResultDto<Integer> updateToDoList(@RequestBody UpdateToDoListReq p){ // @RequestPart(required = false) List<MultipartFile> pics
        log.info("p: {}", p);
        if(p == null) throw new NullPointerException();
        int result = service.updateToDoList(p);
        if(result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 게시물입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK,"게시글 수정완료",result);
    }

    @DeleteMapping
    public ResultDto<Integer> deleteToDoList(@RequestParam Integer listId){
        log.info("listId: {}", listId);
        if(listId == null) throw new NullPointerException();
        int result = service.deleteToDoList(listId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않는 게시물입니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "삭제 완료", result);
    }



}
