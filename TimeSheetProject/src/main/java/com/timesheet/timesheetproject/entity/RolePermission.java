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
public class RolePermission extends Base{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "role_id" , referencedColumnName = "id")
    Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "permission_id" , referencedColumnName = "id")
    Permission permission;
}
