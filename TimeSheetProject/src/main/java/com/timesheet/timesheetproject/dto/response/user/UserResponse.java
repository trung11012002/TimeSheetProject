package com.timesheet.timesheetproject.dto.response.user;

import com.timesheet.timesheetproject.dto.response.role.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    RoleResponse role;
}
