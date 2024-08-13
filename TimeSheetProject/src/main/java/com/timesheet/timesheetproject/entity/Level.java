package com.timesheet.timesheetproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Level extends Base{
    @Column(name = "code" ,nullable = false)
    String code;
    @Column(name = "name",nullable = false)
    String name;

    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<User> users;

    @OneToMany(mappedBy = "beginLevel",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<User> beginUsers;

    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<TypeUserLevel> typeUserLevels;
}
