package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.entity.Permission;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.RolePermission;
import com.timesheet.timesheetproject.mapper.RoleMapper;
import com.timesheet.timesheetproject.repository.PermissionRepository;
import com.timesheet.timesheetproject.repository.RolePermissionRepository;
import com.timesheet.timesheetproject.repository.RoleRepository;
import com.timesheet.timesheetproject.service.IPermissionService;
import com.timesheet.timesheetproject.service.IRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public List<RoleResponse> getRoleByUserId(Long userId) {
        return roleMapper.toRoleResponses(roleRepostitory.findRoleByUserId(userId));
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return roleMapper.toRoleResponses(roleRepostitory.findAll());
    }

    @Override
    public RoleResponse creation(RoleCreationRequest request) {
        //Create role
        Role role = roleMapper.toRole(request);
        role = roleRepostitory.save(role);
        //Created role_permission for role
        if(request.getPermissionIds() != null){
            for(Long permissionId : request.getPermissionIds()){
                Permission permission = permissionRepository.findById(permissionId).get();
                RolePermission rolePermission = new RolePermission(role,permission);
                rolePermissionRepository.save(rolePermission);
            }
        }
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
        return roleMapper.toRoleResponse(roleRepostitory.findById(roleId).get());
    }

    @Override
    public Page<RoleResponse> searchPage(String name, Integer pageNo) {
        if ("".equals(name)) {
            name = null;
        }
        Pageable pageable = PageRequest.of(pageNo - 1 , 4);
        Page<Role> rolePage = roleRepostitory.searchPage(name , pageable);
        return rolePage.map(roleMapper :: toRoleResponse);
    }
}
