package com.timesheet.timesheetproject.dto.request.timesheet;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetUpdateRequest {

    Long id;

    String note;

    Double workingTime;

    Boolean type;

    Long taskId;

    Long projectId;

}
