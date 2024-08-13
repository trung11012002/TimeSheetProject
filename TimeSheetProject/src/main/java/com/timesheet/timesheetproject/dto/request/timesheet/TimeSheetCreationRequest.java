package com.timesheet.timesheetproject.dto.request.timesheet;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetCreationRequest {

    String note;

    Double workingTime;

    Boolean type;

    Long taskId;

    Long projectId;
    LocalDate date;

}
