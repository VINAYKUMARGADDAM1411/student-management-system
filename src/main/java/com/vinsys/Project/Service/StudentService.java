package com.vinsys.Project.Service;

import java.util.List;

import com.vinsys.Project.beans.Student;

public interface StudentService {
    List<Student> getAll();
    Student getById(Long id);
    Student save(Student student);
    void delete(Long id);
    Student getByUsername(String username);
	
}

