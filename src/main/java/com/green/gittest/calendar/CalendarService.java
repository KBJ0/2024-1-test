package com.green.gittest.calendar;

import com.green.gittest.calendar.model.*;
import com.green.gittest.calendar.model.PostCalendarReq;
import com.green.gittest.calendar.model.UpdateCalendarReq;
import com.green.gittest.common.CheckMapper;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CalendarService {
    private final CalendarMapper mapper;
    private final CheckMapper checkMapper;

    public ResultDto<PostCalendarRes> postCalendar(PostCalendarReq p) {
        if (p == null) throw new NullPointerException();
        if(checkMapper.getUserId(p.getUserId()) == 0 ) throw new UserNotFoundException();
        if(checkMapper.getPetId(p.getPetId()) == 0 ) throw new PetNotFoundException();
        mapper.postCalendar(p);
        String msg = "캘린더 일정이 정상적으로 추가 완료되었습니다.";
        PostCalendarRes res = new PostCalendarRes();
        res.setCalendarId(p.getCalendarId());
        return ResultDto.resultDto(HttpStatus.OK, "SU", msg, res);
    }

    public ResultDto<List<GetCalendarRes>> getCalendarFromUserId(long userId){
        if(checkMapper.getUserId(userId) == 0 ) throw new UserNotFoundException();
        List<GetCalendarRes> list = mapper.getCalendarFromUserId(userId);
        String msg = "캘린더 불러오기 성공 verUserid";

        return ResultDto.resultDto(HttpStatus.OK, "SU", msg, list);
    }

    public ResultDto<List<GetCalendarRes>> getCalendarFromPetId(long petId){
        if (petId == 0) throw new NullPointerException();
        List<GetCalendarRes> list = mapper.getCalendarFromPetId(petId);
        String msg = "캘린더 불러오기 성공 verPetId";

        return ResultDto.resultDto(HttpStatus.OK, "SU", msg, list);
    }

    public ResultDto<Integer> updateCalendar(UpdateCalendarReq p){
        if (p == null) throw new NullPointerException();
        mapper.updateCalendar(p);
        String msg = "캘린더 변경 완료";

        return ResultDto.resultDto(HttpStatus.OK, "SU", msg);
    }

    public ResultDto<Integer> deleteCalendar(long calendarId){
        if (calendarId == 0) throw new NullPointerException();
        mapper.deleteCalendar(calendarId);
        String msg = "특정 반려동물의 캘린더를 정상적으로 삭제하였습니다.";

        return ResultDto.resultDto(HttpStatus.OK, "SU", msg);
    }


}
