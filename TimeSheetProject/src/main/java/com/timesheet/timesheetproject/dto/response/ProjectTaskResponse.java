package com.timesheet.timesheetproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectTaskResponse extends BaseResponse{

    TaskResponse task;

    ProjectResponse project;
}
