package com.timesheet.timesheetproject.dto.request.statusmyrequest;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.entity.Base;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusMyRequestCreationRequest  {

    String code;
    String name;

    RoleCreationRequest role;
}
