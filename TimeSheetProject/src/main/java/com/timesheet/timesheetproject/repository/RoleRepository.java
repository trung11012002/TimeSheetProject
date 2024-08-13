package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByCode (String code);
    @Query(value = "SELECT r.* FROM role r JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = :userId", nativeQuery = true)
    List<Role> findRoleByUserId(@Param("userId") Long userId);

    @Query("SELECT r FROM Role r WHERE " +
            "(:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')))" )
    Page<Role> searchPage(
            @Param("name") String name,
            Pageable pageable);
}
