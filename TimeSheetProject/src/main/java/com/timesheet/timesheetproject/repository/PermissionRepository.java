package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Query(value = "SELECT p.* FROM permission p JOIN role_permission rp ON p.id = rp.permission_id WHERE rp.role_id = :roleId", nativeQuery = true)
    List<Permission> findPermissionByRoleIdNative(@Param("roleId") Long roleId);
}
