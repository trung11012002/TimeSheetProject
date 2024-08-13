package com.timesheet.timesheetproject.dto.request.position;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionCreationRequest {
    String name;
    String description;

}
