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
public class Project extends Base{

    String name;
    String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TimeSheet> timeSheets;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProjectTask> projectTasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "team_id" ,referencedColumnName = "id")
    Team team;


}
