package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.entity.Task;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectTaskResponse extends BaseResponse{

    TaskResponse task;

    ProjectResponse project;
}
