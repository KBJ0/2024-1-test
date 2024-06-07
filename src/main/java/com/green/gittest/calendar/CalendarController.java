package com.green.gittest.calendar;

import com.green.gittest.calendar.model.*;
import com.green.gittest.calendar.model.PostCalendarReq;
import com.green.gittest.calendar.model.UpdateCalendarReq;
import com.green.gittest.common.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
@Tag(name = "calendar", description = "calendar CRUD")
public class CalendarController {
    private final CalendarService service;

    @PostMapping
    @Operation(summary = "일정 등록" , description = "추가할 일정을 작성합니다")
    public ResultDto<PostCalendarRes> postCalendar(@RequestBody PostCalendarReq p){
        return service.postCalendar(p);
    }

    @GetMapping("user_id")
    @Operation(summary = "유저 일정 불러오기" , description = "로그인한 유저의 모든 일정을 불러옵니다.\n userId값은 로그인한 유저의 PK 값")
    public ResultDto<List<GetCalendarRes>> getCalendarFromUserId(@RequestParam(name="user_id") Long userId){
        return service.getCalendarFromUserId(userId);
    }
    @GetMapping("pet_id")
    @Operation(summary = "펫 별 일정 불러오기" , description = "선택된 펫의 일정을 불러옵니다. petId값은 펫의 PK 값")
    public ResultDto<List<GetCalendarRes>> getCalendarFromPetId(@RequestParam(name="pet_id") Long petId,
                                                                @RequestParam(name = "user_id") Long userId){
        return service.getCalendarFromPetId(petId, userId);
    }
    @GetMapping("calendar_id")
    @Operation(summary = "특정 캘린더 일정 불러오기" , description = "특정 캘린더 일정을 불러옵니다. calendarId값은 캘린더의 PK 값")
    public ResultDto<GetCalendarRes> getCalendarDetail(@RequestParam(name="calendar_id") Long calendarId){
        return service.getCalendarDetail(calendarId);
    }

    @PatchMapping
    @Operation(summary = "일정 수정" , description = "등록된 일정을 수정합니다")
    public ResultDto<Integer> updateCalendar(@RequestBody UpdateCalendarReq p){
        return service.updateCalendar(p);
    }

    @DeleteMapping
    @Operation(summary = "일정 삭제" , description = "등록된 일정을 삭제합니다")
    public ResultDto<Integer> deleteCalendar(@RequestParam(name = "calendar_id") Long calendarId){
        return service.deleteCalendar(calendarId);
    }


}
