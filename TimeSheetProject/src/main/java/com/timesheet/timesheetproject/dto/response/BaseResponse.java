package com.timesheet.timesheetproject.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse {
    Long id;

    Date createdDate;

    Date modifiedDate;

    String createdBy;

    String modifiedBy;
}
