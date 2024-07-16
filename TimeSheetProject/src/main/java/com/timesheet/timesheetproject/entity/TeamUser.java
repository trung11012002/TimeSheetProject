package com.timesheet.timesheetproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TeamUser extends Base{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "user_id",referencedColumnName = "id")
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "team_id", referencedColumnName = "id")
    Team team;
}

