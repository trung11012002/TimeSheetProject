package com.timesheet.timesheetproject.dto.request.timesheet;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetCreationRequest {

    String note;

    double workingTime;

    String type;


    UserCreationRequest user;


    ProjectResponse project;
}
