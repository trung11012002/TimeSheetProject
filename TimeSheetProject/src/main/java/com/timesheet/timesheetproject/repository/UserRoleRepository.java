package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUserId(Long userId);

}
