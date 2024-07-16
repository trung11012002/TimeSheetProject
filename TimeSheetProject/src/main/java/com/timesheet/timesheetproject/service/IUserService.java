package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserResetPasswordRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.User;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest requet);
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse updateUser(UserUpdateRequest request);
    void deleteUser(Long userId);
    UserResponse getMyInfo();
    UserResponse resetPassword(UserResetPasswordRequest request);
    List<UserResponse> searchUsers(String username, Long positionId, Boolean active, Long userTypeId, Long branchId, Long levelId);
}
