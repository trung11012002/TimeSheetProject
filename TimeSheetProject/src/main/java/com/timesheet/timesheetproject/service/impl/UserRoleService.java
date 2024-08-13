package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.entity.UserRole;
import com.timesheet.timesheetproject.mapper.UserRoleMapper;
import com.timesheet.timesheetproject.repository.RoleRepository;
import com.timesheet.timesheetproject.repository.UserRepository;
import com.timesheet.timesheetproject.repository.UserRoleRepository;
import com.timesheet.timesheetproject.service.IUserRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleService implements IUserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserRoleResponse creation(UserRole userRole) {
        return userRoleMapper.toUserRoleResponse(userRoleRepository.save(userRole));
    }
    @Override
    public List<UserRoleResponse> updateUserRolesByUserId(Long userId, Long[] roleIds) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        //delete all user_role
        userRoleRepository.deleteAll(userRoles);
        //add user_role
        List<UserRole> newUserRoles = new ArrayList<>();
        if (roleIds != null && roleIds.length > 0) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            for (Long roleId : roleIds) {
                Role role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                newUserRoles.add(new UserRole(user, role));
            }
            newUserRoles = userRoleRepository.saveAll(newUserRoles);
        }

        return userRoleMapper.toUserRoleResponses(newUserRoles);
    }
}
