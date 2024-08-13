package com.timesheet.timesheetproject.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "ID_REQUIRED")
    Long id;
//    @Size(min = 3, message = "USERNAME_INVALID")
//    String username;

//    @Size(min = 8, message = "PASSWORD_INVALID")
//    String password;

    @NotNull(message = "SURNAME_REQUIRED")
    String surname;

    @NotNull(message = "NAME_REQUIRED")
    String name;

    @NotNull(message = "EMAIL_REQUIRED")
    @Email(message = "EMAIL_INVALID")
    String email;

//    @DobConstraint(min = 16, message = "INVALID_DOB")
//    LocalDate dob;

    @NotNull(message = "ACTIVE_REQUIRED")
    Boolean active;

    @NotNull(message = "SEX_REQUIRED")
    String sex;

    String basicTrainer;
    LocalDate startDate;
    LocalDate salaryAt;

    @NotNull(message = "ALLOW_LEAVE_DAY_REQUIRED")
    Integer allowLeaveDay;
    Double salary;
    String address;
    String phone;

    Long beginLevelId;
    LocalDate stopWorkingDate;

    // Foreign key
    Long levelId;

    Long typeUserId;
//    @NotNull(message = "ROLE_ID_REQUIRED")
//    Long roleId;

    @NotNull(message = "POSITION_ID_REQUIRED")
    Long positionId;

    @NotNull(message = "BRANCH_ID_REQUIRED")
    Long branchId;
}
