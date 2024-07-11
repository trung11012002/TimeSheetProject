package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.entity.TeamUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamResponse extends BaseResponse{
    String name;
    String desciption;
}
