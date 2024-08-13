package com.timesheet.timesheetproject.dto.request.typeuserlevel;

import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.TypeUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserLevelCreationRequest {
    Long levelId;
    Long typeUserId;
}
