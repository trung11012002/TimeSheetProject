package com.timesheet.timesheetproject.dto.response;

import com.timesheet.timesheetproject.entity.Team;
import com.timesheet.timesheetproject.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamUserResponse extends BaseResponse {

    UserResponse user;

    TeamResponse team;
}
