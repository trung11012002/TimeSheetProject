package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse extends BaseResponse{
    String username;
    String password;
    String surname;
    String name;
    String email;
    LocalDate dob;
    Boolean active;
    String basicTranner;

    LocalDate startDate;
    LocalDate salaryAt;
    int allowLeaveDay;
    double salary;
    String address;
    String phone;
    String beginLevel;
    LocalDate stopWorkingDate;


    LevelResponse level;

    TypeUserResponse typeUser;

    RoleResponse role;

    PositionResponse position;

    BranchResponse branch;

}
