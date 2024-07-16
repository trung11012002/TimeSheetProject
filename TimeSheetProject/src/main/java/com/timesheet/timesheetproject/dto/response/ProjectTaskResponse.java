package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.entity.Task;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
