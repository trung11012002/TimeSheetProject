package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.TypeUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelResponse extends BaseResponse {
    String code;
    String name;
    TypeUserResponse typeUser;
}
