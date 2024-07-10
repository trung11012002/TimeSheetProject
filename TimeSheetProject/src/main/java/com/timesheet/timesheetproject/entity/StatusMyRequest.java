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
public class StatusMyRequest extends Base{
    @Column(name = "name",nullable = false)
    String name;
    @Column(name = "description" ,nullable = false)
    String description;

    @OneToMany(mappedBy = "statusMyRequest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<MyRequest> myRequests;
}
