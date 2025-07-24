package com.vinsys.Project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.Project.beans.Result;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentId(Long studentId);
}
