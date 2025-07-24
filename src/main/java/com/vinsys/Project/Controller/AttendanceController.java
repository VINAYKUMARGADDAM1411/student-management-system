package com.vinsys.Project.Controller;

import com.vinsys.Project.Repo.AttendanceRepository;
import com.vinsys.Project.Repo.CourseRepository;
import com.vinsys.Project.Repo.StudentRepository;
import com.vinsys.Project.beans.Attendance;
import com.vinsys.Project.beans.Course;
import com.vinsys.Project.beans.Student;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/admin/attendance")
    public String viewAllAttendance(Model model) {
        model.addAttribute("attendances", attendanceRepository.findAll());
        return "admin/attendance";  
    }

    @GetMapping("/admin/attendance/add")
    public String showAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/attendance_form";
    }

    @PostMapping("/admin/attendance/save")
    public String saveAttendance(
            @RequestParam(required = false) Long id,
            @RequestParam String date,
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestParam(required = false) boolean present
    ) {
        Attendance attendance = (id != null) ?
                attendanceRepository.findById(id).orElse(new Attendance()) :
                new Attendance();

        attendance.setDate(LocalDate.parse(date));
        attendance.setPresent(present);

        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        attendance.setStudent(student);
        attendance.setCourse(course);

        attendanceRepository.save(attendance);

        return "redirect:/admin/attendance";
    }




    @GetMapping("/admin/attendance/edit/{id}")
    public String editAttendance(@PathVariable Long id, Model model) {
        Attendance attendance = attendanceRepository.findById(id).orElse(null);
        if (attendance == null) {
            return "redirect:/admin/attendance";
        }
        model.addAttribute("attendance", attendance);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/attendance_form";
    }

    @GetMapping("/admin/attendance/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceRepository.deleteById(id);
        return "redirect:/admin/attendance";
    }
}
