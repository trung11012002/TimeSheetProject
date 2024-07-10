package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.user.UserResponse;
import com.timesheet.timesheetproject.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(UserCreationRequest requet);
    List<UserResponse> getAllUser();
    UserResponse getUser(int id);
    UserResponse updateUser(int userId,UserUpdateRequest request);
    void deleteUser(int userId);
    UserResponse getMyInfo();
}
