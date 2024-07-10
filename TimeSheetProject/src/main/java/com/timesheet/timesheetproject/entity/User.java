package com.timesheet.timesheetproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id" ,referencedColumnName = "id")
    Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_user_id" ,referencedColumnName = "id")
    TypeUser typeUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    Role role;

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

}

