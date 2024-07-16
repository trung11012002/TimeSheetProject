package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.mapper.RoleMapper;
import com.timesheet.timesheetproject.repository.RoleRepository;
import com.timesheet.timesheetproject.service.IRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepostitory;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getRoleByUserId(Long userId) {
        return roleMapper.toRoleResponses(roleRepostitory.findRoleByUserId(userId));
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return roleMapper.toRoleResponses(roleRepostitory.findAll());
    }
}
