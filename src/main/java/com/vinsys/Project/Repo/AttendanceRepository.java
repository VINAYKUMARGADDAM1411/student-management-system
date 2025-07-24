package com.vinsys.Project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.Project.beans.Attendance;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
}
