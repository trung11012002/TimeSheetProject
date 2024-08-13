package com.timesheet.timesheetproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User extends Base{
    String username;
    String password;
    String email;
    String name;
    String surname;
    Boolean active;
    String basicTranner;
    String sex;
    LocalDate dob;

    LocalDate startDate;
    LocalDate salaryAt;
    int allowLeaveDay;
    double salary;
    String address;
    String phone;
    LocalDate stopWorkingDate;
    String avatarUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "begin_level_id" ,referencedColumnName = "id")
    Level beginLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id" ,referencedColumnName = "id")
    Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_user_id" ,referencedColumnName = "id")
    TypeUser typeUser;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<UserRole> userRoles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id" ,referencedColumnName = "id")
    Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id" ,referencedColumnName = "id")
    Branch branch;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<TimeSheet> timeSheets;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<MyRequest> myRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TeamUser> teamUsers;

}

