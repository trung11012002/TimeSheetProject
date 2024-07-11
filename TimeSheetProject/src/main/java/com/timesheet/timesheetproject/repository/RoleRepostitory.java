package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepostitory extends JpaRepository<Role,Long> {
    Optional<Role> findByCode (String code);
}
