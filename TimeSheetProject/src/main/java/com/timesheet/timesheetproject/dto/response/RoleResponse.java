package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Permission;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse extends BaseResponse{
    String code;
    String name;
}
