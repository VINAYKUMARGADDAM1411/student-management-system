package com.vinsys.Project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.Project.Repo.AttendanceRepository;
import com.vinsys.Project.Service.AttendanceService;
import com.vinsys.Project.beans.Attendance;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public Attendance getById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @Override
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }
}
