package com.vinsys.Project.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.Project.beans.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
