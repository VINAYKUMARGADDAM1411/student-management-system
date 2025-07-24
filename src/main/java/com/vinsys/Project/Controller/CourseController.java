package com.vinsys.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vinsys.Project.Repo.CourseRepository;
import com.vinsys.Project.beans.Course;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String redirectToList() {
        return "redirect:/admin/courses/list";
    }

    @GetMapping("/list")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/courses"; 
    }

    @GetMapping("/add")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "admin/course_form"; 
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseRepository.save(course);
        return "redirect:/admin/courses/list";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
            return "admin/course_form";
        } else {
            return "redirect:/admin/courses/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "redirect:/admin/courses/list";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseRepository.findAll());
    }
}
