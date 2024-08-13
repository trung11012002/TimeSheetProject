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
public class TypeUserLevel extends Base{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "level_id", referencedColumnName = "id")
    Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "type_user_id", referencedColumnName = "id")
    TypeUser typeUser;
}
