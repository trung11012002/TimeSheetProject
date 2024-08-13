package com.timesheet.timesheetproject.dto.response;

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
public class TypeUserLevelResponse extends BaseResponse{

    Level level;

    TypeUser typeUser;
}
