package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.StatusMyRequest;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestResponse extends BaseResponse{

    LocalDate date;
    double lateHour;

    UserResponse user;

    StatusMyRequestResponse statusMyRequest;
}
