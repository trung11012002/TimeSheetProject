package com.timesheet.timesheetproject.mapper;

import com.timesheet.timesheetproject.dto.request.level.LevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.typeuser.TypeUserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.TypeUserResponse;
import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.TypeUser;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeUserMapper {
    TypeUser toTypeUser(TypeUserCreationRequest typeUserCreationRequest);
    TypeUserResponse toTypeUserResponse(TypeUser typeUser);

    List<TypeUser> toTypeUsers(List<TypeUserCreationRequest> typeUserCreationRequests);
    List<TypeUserResponse> toTypeUserResponses(List<TypeUser> typeUsers);
}
