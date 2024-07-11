package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Position;
import com.timesheet.timesheetproject.entity.ProjectTask;
import com.timesheet.timesheetproject.entity.Team;
import com.timesheet.timesheetproject.entity.TimeSheet;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse extends BaseResponse{
    String name;
    String description;

    TeamResponse team;
}
