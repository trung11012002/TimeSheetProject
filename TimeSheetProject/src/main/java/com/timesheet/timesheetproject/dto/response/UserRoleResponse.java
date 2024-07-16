package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleResponse extends BaseResponse{
    User user;
    Role role;
}
