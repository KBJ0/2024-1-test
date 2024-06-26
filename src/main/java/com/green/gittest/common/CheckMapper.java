package com.green.gittest.common;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckMapper {
    Long getUserId(long userId);
    Long getPetId(long petId);
    Long getCalendarId(long calendarId);
    Long getListId(long listId);
    String checkNickname(String nickname);
    Integer getToDoListIsCompleted(long listId);
}
