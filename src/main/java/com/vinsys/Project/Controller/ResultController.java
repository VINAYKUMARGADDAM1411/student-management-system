package com.vinsys.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vinsys.Project.Repo.CourseRepository;
import com.vinsys.Project.Repo.ResultRepository;
import com.vinsys.Project.Repo.StudentRepository;
import com.vinsys.Project.beans.Course;
import com.vinsys.Project.beans.Result;
import com.vinsys.Project.beans.Student;

import java.security.Principal;
import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

   
    @GetMapping("/admin/results")
    public String listResults(Model model) {
        model.addAttribute("results", resultRepository.findAll());
        return "admin/results";
    }

   
    @GetMapping("/admin/results/add")
    public String addResultForm(Model model) {
        model.addAttribute("result", new Result());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/result_form";
    }

   
    @PostMapping("/admin/results/save")
    public String saveResult(@ModelAttribute Result result) {
        if (result.getStudent() != null && result.getStudent().getId() != null) {
            Student student = studentRepository.findById(result.getStudent().getId()).orElse(null);
            result.setStudent(student);
        }

        if (result.getCourse() != null && result.getCourse().getId() != null) {
            Course course = courseRepository.findById(result.getCourse().getId()).orElse(null);
            result.setCourse(course);
        }

        resultRepository.save(result);
        return "redirect:/admin/results";
    }
    
    @GetMapping("/admin/results/edit/{id}")
    public String editResult(@PathVariable Long id, Model model) {
        Result result = resultRepository.findById(id).orElse(null);
        if (result == null) {
            return "redirect:/admin/results";
        }
        model.addAttribute("result", result);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/result_form";
    }

    @GetMapping("/admin/results/delete/{id}")
    public String deleteResult(@PathVariable Long id) {
        resultRepository.deleteById(id);
        return "redirect:/admin/results";
    }

    @GetMapping("/student/results/api")
    @ResponseBody
    public ResponseEntity<List<Result>> getStudentResultsApi(Principal principal) {
        Student student = studentRepository.findByUserUsername(principal.getName());
        if (student != null) {
            return ResponseEntity.ok(resultRepository.findByStudentId(student.getId()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
