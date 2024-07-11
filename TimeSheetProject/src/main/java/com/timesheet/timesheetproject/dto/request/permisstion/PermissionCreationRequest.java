package com.timesheet.timesheetproject.dto.request.permisstion;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.entity.Base;
import com.timesheet.timesheetproject.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionCreationRequest  {

    String code;
    String name;

    RoleCreationRequest role;
}
