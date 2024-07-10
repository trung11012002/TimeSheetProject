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
public class Level extends Base{
    @Column(name = "code" ,nullable = false)
    String code;
    @Column(name = "name",nullable = false)
    String name;

    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "type_user_id" , referencedColumnName = "id")
    TypeUser typeUser;
}
