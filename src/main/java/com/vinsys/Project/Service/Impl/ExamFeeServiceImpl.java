package com.vinsys.Project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.Project.Repo.ExamFeeRepository;
import com.vinsys.Project.Service.ExamFeeService;
import com.vinsys.Project.beans.ExamFee;

import java.util.List;

@Service
public class ExamFeeServiceImpl implements ExamFeeService {

    @Autowired
    private ExamFeeRepository examFeeRepository;

    @Override
    public List<ExamFee> getAll() {
        return examFeeRepository.findAll();
    }

    @Override
    public List<ExamFee> getByStudentId(Long studentId) {
        return examFeeRepository.findByStudentId(studentId);
    }

    @Override
    public ExamFee getById(Long id) {
        return examFeeRepository.findById(id).orElse(null);
    }

    @Override
    public ExamFee save(ExamFee examFee) {
        return examFeeRepository.save(examFee);
    }

    @Override
    public void delete(Long id) {
        examFeeRepository.deleteById(id);
    }
}
