package com.green.gittest.todolist;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.todolist.model.PostDoToListReq;
import com.green.gittest.todolist.model.PostToDoListRes;
import com.green.gittest.user.model.SignUpPostReq;
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

 // API 명세서 Response 값을 int에서 수정해줘야 할듯.-> 뭐로 바꿔야하는지 코드실행하고 확인해봐야함.
    @PostMapping
    public ResultDto<PostToDoListRes> postToDoList(@RequestPart(required = false) List<MultipartFile> pics
            , @RequestPart PostDoToListReq p){
        log.info("pics: {}", pics); log.info("p: {}", p);

        if(p == null) throw new NullPointerException();
        PostToDoListRes result = service.postToDoList(pics, p);
        return ResultDto.resultDto(HttpStatus.OK,"게시글 작성",result);
    }

}
