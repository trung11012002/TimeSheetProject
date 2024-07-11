package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserResponse extends BaseResponse {
    String code;
    String name;
}
