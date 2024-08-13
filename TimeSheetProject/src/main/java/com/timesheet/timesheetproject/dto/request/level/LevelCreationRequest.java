package com.timesheet.timesheetproject.dto.request.level;

import com.timesheet.timesheetproject.dto.request.typeuser.TypeUserCreationRequest;
import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.dto.response.TypeUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelCreationRequest  {
    String code;
    String name;
    TypeUserCreationRequest typeUser;
}
