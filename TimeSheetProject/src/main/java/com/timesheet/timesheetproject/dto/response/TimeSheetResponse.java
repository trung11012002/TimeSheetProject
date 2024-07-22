package com.timesheet.timesheetproject.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetResponse extends BaseResponse{
    String note;
    double workingTime;
    Boolean type;
    String status;
    LocalDate date;

    private UserResponse user;
    private ProjectResponse project;
    private TaskResponse task;

}
