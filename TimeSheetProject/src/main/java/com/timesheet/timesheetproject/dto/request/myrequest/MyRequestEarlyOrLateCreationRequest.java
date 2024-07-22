package com.timesheet.timesheetproject.dto.request.myrequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestEarlyOrLateCreationRequest {
    LocalDate date;
    Double hour;
    String type;
}
