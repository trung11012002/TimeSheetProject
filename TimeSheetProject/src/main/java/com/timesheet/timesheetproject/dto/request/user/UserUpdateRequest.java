package com.timesheet.timesheetproject.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.FilterDef;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
