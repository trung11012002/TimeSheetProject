package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleResponse extends BaseResponse{
    User user;
    Role role;
}
