package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level,Long> {
    @Query(value = "SELECT l.* FROM level l JOIN type_user_level tul ON l.id = tul.level_id WHERE tul.type_user_id = :typeUserId", nativeQuery = true)
    List<Level> findLevelsByTypeUserIdNative(@Param("typeUserId") Long typeUserId);
}
