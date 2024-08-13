package com.timesheet.timesheetproject.entity;

import jakarta.persistence.*;
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
public class ProjectTask extends Base{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "task_id" ,referencedColumnName = "id")
    Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "project_id" ,referencedColumnName = "id")
    Project project;
}
