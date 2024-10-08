package com.timesheet.timesheetproject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timesheet.timesheetproject.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    String avatarUrl;

    Level beginLevel;
    LevelResponse level;

    TypeUserResponse typeUser;

    PositionResponse position;

    BranchResponse branch;

    List<RoleResponse> roles;
    List<PermissionResponse> permissions;

    public UserResponse(String username, String password, List<RoleResponse> roles, List<PermissionResponse> permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }

    public UserResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserResponse(String username, String password, int allowLeaveDay, double salary) {
        this.username = username;
        this.password = password;
        this.allowLeaveDay = allowLeaveDay;
        this.salary = salary;
    }
}
