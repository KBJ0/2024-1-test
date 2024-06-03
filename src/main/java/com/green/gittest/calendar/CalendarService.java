package com.green.gittest.calendar;

import com.green.gittest.calendar.model.GetCalendarRes;
import com.green.gittest.calendar.model.PostCalendarReq;
import com.green.gittest.calendar.model.UpdateCalendarReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CalendarService {
    private final CalendarMapper mapper;
    public int postCalendar(PostCalendarReq p) {
        return mapper.postCalendar(p);
    }

    public List<GetCalendarRes> getCalendarFromUserId(long userId){
        return mapper.getCalendarFromUserId(userId);
    }

    public List<GetCalendarRes> getCalendarFromPetId(long petId){
        return mapper.getCalendarFromPetId(petId);
    }

    public int updateCalendar(UpdateCalendarReq p){
        return mapper.updateCalendar(p);
    }

    public int deleteCalendar(long calendarId){
        return mapper.deleteCalendar(calendarId);
    }


}
