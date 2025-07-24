package com.vinsys.Project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.Project.Repo.ResultRepository;
import com.vinsys.Project.Service.ResultService;
import com.vinsys.Project.beans.Result;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public List<Result> getByStudentId(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    @Override
    public Result getById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void delete(Long id) {
        resultRepository.deleteById(id);
    }
}
