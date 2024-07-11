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
public class Position extends Base{
    @Column(name = "name",nullable = false)
    String name;
    @Column(name = "description" ,nullable = false)
    String description;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<User> users;

}
