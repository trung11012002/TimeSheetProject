package com.timesheet.timesheetproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionResponse extends BaseResponse {
    String name;
    String description;

}
