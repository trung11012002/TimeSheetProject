package com.timesheet.timesheetproject.dto.request.branch;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchCreationRequest {
    String name;
    String code;
}
