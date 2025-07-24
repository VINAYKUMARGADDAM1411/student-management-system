package com.vinsys.Project.Service;

import java.util.List;

import com.vinsys.Project.beans.Result;

public interface ResultService {
    List<Result> getAll();
    List<Result> getByStudentId(Long studentId);
    Result getById(Long id);
    Result save(Result result);
    void delete(Long id);
}
