package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.Column;
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
public class TimeSheetResponse extends BaseResponse{

    String note;

    double workingTime;

    String type;


    private UserResponse user;


    private ProjectResponse project;
}
