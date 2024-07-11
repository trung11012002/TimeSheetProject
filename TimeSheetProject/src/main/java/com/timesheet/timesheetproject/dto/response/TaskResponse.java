package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.ProjectTask;
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
public class TaskResponse extends BaseResponse{

    String name;
    String code;
    String description;
}
