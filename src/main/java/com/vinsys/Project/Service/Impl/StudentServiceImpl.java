package com.vinsys.Project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinsys.Project.Repo.StudentRepository;
import com.vinsys.Project.Service.StudentService;
import com.vinsys.Project.beans.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student student) {
        if (student.getUser() != null) {
            String rawPassword = student.getUser().getPassword();

            if (rawPassword != null && !rawPassword.isEmpty()) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                student.getUser().setPassword(encodedPassword);
            } else {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }

            // Set default role if not set
            if (student.getUser().getRole() == null) {
                student.getUser().setRole(com.vinsys.Project.beans.User.Role.STUDENT); // ✅ fixed
            }
        } else {
            throw new IllegalArgumentException("User details are missing for the student");
        }

        return studentRepository.save(student);
    }



    @Override
    public Student getByUsername(String username) {
        return studentRepository.findByUserUsername(username);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
