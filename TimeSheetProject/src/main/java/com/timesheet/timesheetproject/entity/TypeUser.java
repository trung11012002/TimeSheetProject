package com.timesheet.timesheetproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TypeUser extends Base{
    @Column(name = "code" ,nullable = false)
    String code;
    @Column(name = "name",nullable = false)
    String name;

    @OneToMany(mappedBy = "typeUser",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<Level>levels;

    @OneToMany(mappedBy = "typeUser" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<User> users;

}
