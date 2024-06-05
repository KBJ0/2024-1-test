package com.green.gittest.common;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckMapper {
    int getUserId(long userId);
    int getPetId(long petId);
    int getCalendarId(long calendarId);
}
