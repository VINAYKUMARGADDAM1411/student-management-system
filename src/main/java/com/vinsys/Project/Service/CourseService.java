package com.vinsys.Project.Service;

import java.util.List;

import com.vinsys.Project.beans.Course;

public interface CourseService {
    List<Course> getAll();
    Course getById(Long id);
    Course save(Course course);
    void delete(Long id);
}
