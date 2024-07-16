package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE " +
            "(:username IS NULL OR u.username = :username) AND " +
            "(:positionId IS NULL OR u.position.id = :positionId) AND " +
            "(:active IS NULL OR u.active = :active) AND " +
            "(:userTypeId IS NULL OR u.typeUser.id = :userTypeId) AND " +
            "(:branchId IS NULL OR u.branch.id = :branchId) AND " +
            "(:levelId IS NULL OR u.level.id = :levelId)")
    List<User> searchUsers(
            @Param("username") String username,
            @Param("positionId") Long positionId,
            @Param("active") Boolean active,
            @Param("userTypeId") Long userTypeId,
            @Param("branchId") Long branchId,
            @Param("levelId") Long levelId);
}
