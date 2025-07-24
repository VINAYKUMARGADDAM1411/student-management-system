package com.vinsys.Project.Service;

import java.util.List;

import com.vinsys.Project.beans.ExamFee;

public interface ExamFeeService {
    List<ExamFee> getAll();
    List<ExamFee> getByStudentId(Long studentId);
    ExamFee getById(Long id);
    ExamFee save(ExamFee examFee);
    void delete(Long id);
}
