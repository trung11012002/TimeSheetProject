package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.PermissionResponse;
import com.timesheet.timesheetproject.mapper.PermissionMapper;
import com.timesheet.timesheetproject.repository.PermissionRepository;
import com.timesheet.timesheetproject.service.IPermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionService implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionRepository permissionRepostitorys;

    @Override
    public List<PermissionResponse> getAllPermission() {
        return permissionMapper.toPermissions(permissionRepostitorys.findAll());
    }

    @Override
    public List<PermissionResponse> getPermissionByRoleId(Long roleId) {
        return permissionMapper.toPermissions(permissionRepostitorys.findPermissionByRoleIdNative(roleId));
    }
}
