package com.timesheet.timesheetproject.dto.request.shedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleConfigUpdateRequest {
    private Long id;
    private String cronExpression;
    private String taskName;
}