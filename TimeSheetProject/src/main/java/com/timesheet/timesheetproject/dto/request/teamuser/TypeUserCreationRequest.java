package com.timesheet.timesheetproject.dto.request.teamuser;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.dto.response.TeamResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserCreationRequest  {
    UserResponse user;

    TeamResponse team;
}
