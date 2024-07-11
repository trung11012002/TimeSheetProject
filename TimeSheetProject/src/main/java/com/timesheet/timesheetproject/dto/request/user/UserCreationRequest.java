package com.timesheet.timesheetproject.dto.request.user;

import com.timesheet.timesheetproject.dto.request.branch.BranchCreationRequest;
import com.timesheet.timesheetproject.dto.request.level.LevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.position.PositionCreationRequest;
import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.request.typeuser.TypeUserCreationRequest;
import com.timesheet.timesheetproject.entity.*;
import com.timesheet.timesheetproject.validator.DobConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    @NotNull(message = "SURNAME_REQUIRED")
    String surname;

    @NotNull(message = "NAME_REQUIRED")
    String name;

    @NotNull(message = "EMAIL_REQUIRED")
    @Email(message = "EMAIL_INVALID")
    String email;

    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;

    @NotNull(message = "ACTIVE_REQUIRED")
    Boolean active;

    @NotNull(message = "BASIC_TRAINER_REQUIRED")
    String basicTrainer;

    @NotNull(message = "START_DATE_REQUIRED")
    LocalDate startDate;

    @NotNull(message = "SALARY_AT_REQUIRED")
    LocalDate salaryAt;

    @NotNull(message = "ALLOW_LEAVE_DAY_REQUIRED")
    Integer allowLeaveDay;

    @NotNull(message = "SALARY_REQUIRED")
    Double salary;

    @NotNull(message = "ADDRESS_REQUIRED")
    String address;

    @NotNull(message = "PHONE_REQUIRED")
    String phone;

    @NotNull(message = "BEGIN_LEVEL_REQUIRED")
    String beginLevel;

    @NotNull(message = "STOP_WORKING_DATE_REQUIRED")
    LocalDate stopWorkingDate;

    // Foreign key
    @NotNull(message = "LEVEL_ID_REQUIRED")
    long levelId;

    @NotNull(message = "TYPE_USER_ID_REQUIRED")
    long typeUserId;

    @NotNull(message = "ROLE_ID_REQUIRED")
    long roleId;

    @NotNull(message = "POSITION_ID_REQUIRED")
    long positionId;

    @NotNull(message = "BRANCH_ID_REQUIRED")
    long branchId;

}
