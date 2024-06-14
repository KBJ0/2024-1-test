package com.green.gittest.calendar;

import com.green.gittest.calendar.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

    int postCalendar(PostCalendarReq p);
    List<GetCalendarRes> getCalendarFromUserId(long userId);
    List<GetCalendarRes> getCalendarFromPetId(long petId);
    int updateCalendar(UpdateCalendarReq p);
    int deleteCalendar(long calendarId);
    GetCalendarDetailRes getCalendarDetail(long calendarId);

}
