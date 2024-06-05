package com.green.gittest.calendar;

import com.green.gittest.calendar.model.GetCalendarRes;
import com.green.gittest.calendar.model.PostCalendarReq;
import com.green.gittest.calendar.model.UpdateCalendarReq;
import com.green.gittest.common.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
@Tag(name = "calendar", description = "calendar CRUD")
public class CalendarController {
    private final CalendarService service;

    @PostMapping
    public ResultDto<Integer> postCalendar(@RequestBody PostCalendarReq p){
        log.info("p: {}", p);
        if (p == null) throw new NullPointerException();

        int result = service.postCalendar(p);
        return ResultDto.resultDto(HttpStatus.OK,"캘린더 작성 완료", result);
    }

    @GetMapping("user_id")
    public ResultDto<List<GetCalendarRes>> getCalendarFromUserId(@RequestParam(name="user_id") Long userId){
        log.info("userId: {}", userId);
        if (userId == null) throw new NullPointerException();

        List<GetCalendarRes> result = service.getCalendarFromUserId(userId);
        return ResultDto.resultDto(HttpStatus.OK,"캘린더 불러오기 성공 verUserid", result);
    }
    @GetMapping("pet_id")
    public ResultDto<List<GetCalendarRes>> getCalendarFromPetId(@RequestParam(name="pet_id") Long petId){
        log.info("petId: {}", petId);
        if (petId == null) throw new NullPointerException();

        List<GetCalendarRes> result = service.getCalendarFromPetId(petId);
        return ResultDto.resultDto(HttpStatus.OK,"캘린더 불러오기 성공 verPetId", result);
    }
    @PatchMapping
    public ResultDto<Integer> updateCalendar(@RequestBody UpdateCalendarReq p){
        log.info("p: {}", p);
        if (p == null) throw new NullPointerException();

        int result = service.updateCalendar(p);
        return ResultDto.resultDto(HttpStatus.OK,"캘린더 변경 완료", result);
    }
    @DeleteMapping
    public ResultDto<Integer> deleteCalendar(@RequestParam Long calendarId){
        log.info("calendarId: {}", calendarId);
        if (calendarId == null) throw new NullPointerException();

        int result = service.deleteCalendar(calendarId);
        return ResultDto.resultDto(HttpStatus.OK,"캘린더 삭제 완료", result);
    }


}
