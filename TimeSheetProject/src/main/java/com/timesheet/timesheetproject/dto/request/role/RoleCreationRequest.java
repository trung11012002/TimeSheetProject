package com.timesheet.timesheetproject.dto.request.role;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreationRequest{
    String code;
    String name;
    Long permissionIds [];
}
