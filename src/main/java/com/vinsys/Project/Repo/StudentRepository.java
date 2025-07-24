package com.vinsys.Project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.Project.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserUsername(String username); 
}
