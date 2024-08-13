package com.timesheet.timesheetproject.dto.request.shedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleConfigCreateRequest {
    private String cronExpression;
    private String taskName;
}