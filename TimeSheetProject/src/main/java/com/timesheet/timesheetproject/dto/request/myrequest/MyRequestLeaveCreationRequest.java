package com.timesheet.timesheetproject.dto.request.myrequest;

import com.timesheet.timesheetproject.validator.ValidTypeMyRequestOff;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestLeaveCreationRequest {
    LocalDate date;
    @ValidTypeMyRequestOff(message = "INVALID_TYPE_MYREQUEST_OFF")
    String type;
}
