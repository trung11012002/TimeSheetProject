package com.timesheet.timesheetproject.dto.request.typeuserlevel;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserLevelRequest {

    Long id;
    Long levelId;
    Long typeUserId;

}
