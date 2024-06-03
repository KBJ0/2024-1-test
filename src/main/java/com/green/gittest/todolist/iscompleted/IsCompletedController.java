package com.green.gittest.todolist.iscompleted;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListReq;
import com.green.gittest.todolist.iscompleted.model.GetIsCompletedListRes;
import com.green.gittest.todolist.iscompleted.model.ToggleIsCompletedReq;
import com.green.gittest.todolist.model.GetToDoListRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/todolist/is_complete")
public class IsCompletedController {
    private final IsCompletedService service;

    @PatchMapping
    @Operation(summary = "일정 완료 체크" , description = "Toggle 처리, " +
            "결과가 0 이다 >> 체크 > 비체크 : 체크 취소" + "결과가 1 이다 >> 비체크 > 체크 : 체크 등록")
    public ResultDto<Integer> toggleIsCompleted(@ParameterObject @ModelAttribute ToggleIsCompletedReq p){
        int result = service.toggleIsCompleted(p);
        //result == 0 >> isCompleted 취소 (체크  >비체크) : 체크 취소
        //result == 1 >> isCompleted 추가 (비체크  >체크) : 체크 추가
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();

    }

    @GetMapping
    @Operation(summary = "완료된 일정 불러오기" , description = "완료된 일정만 불러옵니다.")
    public ResultDto<List<GetIsCompletedListRes>> getIsCompletedList(@ParameterObject @ModelAttribute GetIsCompletedListReq p){
        List<GetIsCompletedListRes> list = service.getIsCompletedList(p);

        return ResultDto.<List<GetIsCompletedListRes>>builder().
                statusCode(HttpStatus.OK).
                resultMsg(HttpStatus.OK.toString()).
                resultData(list).
                build();
    }
}
