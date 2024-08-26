package com.sparta.spring_mastery_task.dto.scheduleGetAllDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleGetAllDtoResponse {
    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;


}
