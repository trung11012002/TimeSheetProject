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
public class MyRequest extends Base{
    @Column(name = "date",nullable = false)
    LocalDate date;
    @Column(name = "late_hour" ,nullable = true)
    double lateHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "user_id" , referencedColumnName = "id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "status_my_request_id" , referencedColumnName = "id")
    StatusMyRequest statusMyRequest;

}
