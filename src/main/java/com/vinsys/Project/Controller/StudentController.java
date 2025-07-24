package com.vinsys.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinsys.Project.Repo.*;
import com.vinsys.Project.beans.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ResultRepository resultRepository;


    @Autowired
    private ExamFeeRepository examFeeRepository;

   
    @GetMapping("/dashboard")
    public String studentDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentRepository.findByUserUsername(username);
        model.addAttribute("student", student);
        model.addAttribute("title", "Student Dashboard");
        return "student/dashboard";
    }

 
    @GetMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentRepository.findByUserUsername(username);
        model.addAttribute("student", student);
        return "student/profile";
    }

 
    @GetMapping("/attendance")
    public String viewAttendance(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentRepository.findByUserUsername(username);
        List<Attendance> attendanceList = attendanceRepository.findByStudentId(student.getId());
        model.addAttribute("attendanceList", attendanceList);
        return "student/attendance";
    }

 
    @GetMapping("/results")
    public String viewResults(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentRepository.findByUserUsername(username);
        List<Result> results = resultRepository.findByStudentId(student.getId());
        model.addAttribute("results", results);
        return "student/results";
    }

 
    @GetMapping("/examfee")
    public String viewExamFee(Authentication authentication, Model model) {
        String username = authentication.getName();
        Student student = studentRepository.findByUserUsername(username);
        List<ExamFee> examFees = examFeeRepository.findByStudentId(student.getId());
        model.addAttribute("examFees", examFees);
        return "student/examfee";
    }
}
