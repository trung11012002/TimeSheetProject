package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    String sex;
    LocalDate startDate;
    LocalDate salaryAt;
    int allowLeaveDay;
    double salary;
    String address;
    String phone;
    LocalDate stopWorkingDate;

    Level beginLevel;
    LevelResponse level;

    TypeUserResponse typeUser;

    List<RoleResponse> roles;

    PositionResponse position;

    BranchResponse branch;

}
