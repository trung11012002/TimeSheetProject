package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.dto.response.UserProjection;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<UserProjection> findProjectionByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u")
    List<UserProjection> findUserProjectionAll();

    @Query("SELECT u FROM User u WHERE " +
            "(:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))) AND " +
            "(:positionId IS NULL OR u.position.id = :positionId) AND " +
            "(:active IS NULL OR u.active = :active) AND " +
            "(:userTypeId IS NULL OR u.typeUser.id = :userTypeId) AND " +
            "(:branchId IS NULL OR u.branch.id = :branchId) AND " +
            "(:levelId IS NULL OR u.level.id = :levelId)")
    Page<User> searchUsers(
            @Param("username") String username,
            @Param("positionId") Long positionId,
            @Param("active") Boolean active,
            @Param("userTypeId") Long userTypeId,
            @Param("branchId") Long branchId,
            @Param("levelId") Long levelId,
            Pageable pageable);

    @Query(value = "SELECT u.* FROM user u JOIN user_role ur ON u.id = ur.user_id WHERE ur.role_id = :roleId", nativeQuery = true)
    List<User> findUserByRoleId(@Param("roleId") Long roldId);
}
