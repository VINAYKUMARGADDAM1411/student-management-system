package com.vinsys.Project.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.Project.beans.ExamFee;

import java.util.List;

public interface ExamFeeRepository extends JpaRepository<ExamFee, Long> {
    List<ExamFee> findByStudentId(Long studentId);
}
