package com.vinsys.Project.Controller;

import com.vinsys.Project.Service.AttendanceService;
import com.vinsys.Project.Service.CourseService;
import com.vinsys.Project.Service.StudentService;
import com.vinsys.Project.beans.Attendance;
import com.vinsys.Project.beans.Course;
import com.vinsys.Project.beans.Student;
import com.vinsys.Project.beans.User;
import com.vinsys.Project.beans.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        return "admin/dashboard";
    }


    @GetMapping("/students/list")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "admin/students";
    }

    @GetMapping("/students/add")
    public String showAddStudentForm(Model model) {
        Student student = new Student();
        if (student.getUser() == null) {
            User user = new User();
            user.setRole(Role.STUDENT);
            student.setUser(user);
        }
        model.addAttribute("student", student);
        return "admin/student-form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        if (student.getUser() != null && student.getUser().getRole() == null) {
            student.getUser().setRole(Role.STUDENT);
        }
        studentService.save(student);
        return "redirect:/admin/students/list";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getById(id);
        if (student == null) {
            return "redirect:/admin/students/list";
        }
        model.addAttribute("student", student);
        return "admin/student-form";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/admin/students/list";
    }
   
}
