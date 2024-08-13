package com.timesheet.timesheetproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestResponse extends BaseResponse{
    LocalDate date;
    String type;
    Double hour;
    String status;
    UserResponse user;

    public MyRequestResponse(LocalDate date, String type, Double hour, String status) {
        this.date = date;
        this.type = type;
        this.hour = hour;
        this.status = status;
    }
}
