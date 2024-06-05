package com.green.gittest.calendar;

import com.green.gittest.calendar.model.GetCalendarRes;
import com.green.gittest.calendar.model.PostCalendarReq;
import com.green.gittest.calendar.model.PostCalendarRes;
import com.green.gittest.calendar.model.UpdateCalendarReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

    int postCalendar(PostCalendarReq p);
    List<GetCalendarRes> getCalendarFromUserId(long userId);
    List<GetCalendarRes> getCalendarFromPetId(long petId);
    int updateCalendar(UpdateCalendarReq p);
    int deleteCalendar(long calendarId);

}
