package com.green.gittest.calendar.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class GetCalendarDetailRes {
    private long calendarId;
    private long petId;
    private String petName;
    private String title;
    private String content;
    @Schema(example = "2024-05-30", description = "날짜", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date startDate;
    @JsonDeserialize
    @Schema(example = "12:00:00", description = "시간", requiredMode = Schema.RequiredMode.REQUIRED)
    private Time startTime;
}
