package com.vinsys.Project.Service;

import java.util.List;

import com.vinsys.Project.beans.Attendance;

public interface AttendanceService {
    List<Attendance> getAll();
    List<Attendance> getByStudentId(Long studentId);
    Attendance getById(Long id);
    Attendance save(Attendance attendance);
    void delete(Long id);
}
