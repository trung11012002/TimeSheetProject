package com.timesheet.timesheetproject.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleConfigResponse {
    Long id;
    private String cronExpression;
    private String taskName;
}