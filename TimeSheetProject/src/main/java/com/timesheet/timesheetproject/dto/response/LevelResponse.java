package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.TypeUser;
import com.timesheet.timesheetproject.entity.TypeUserLevel;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelResponse extends BaseResponse {
    String code;
    String name;
}
