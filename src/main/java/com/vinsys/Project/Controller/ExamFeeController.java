package com.vinsys.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vinsys.Project.Repo.ExamFeeRepository;
import com.vinsys.Project.Repo.StudentRepository;
import com.vinsys.Project.beans.ExamFee;

import java.security.Principal;
import java.util.List;

@Controller
public class ExamFeeController {

    @Autowired
    private ExamFeeRepository examFeeRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping("/admin/fees")
    public String redirectToExamFees() {
        return "redirect:/admin/examfees";
    }


    @GetMapping("/admin/examfees")
    public String viewAllFees(Model model) {
        model.addAttribute("examFees", examFeeRepository.findAll());
        return "admin/examfees";
    }

    @GetMapping("/admin/examfees/add")
    public String showAddFeeForm(Model model) {
        model.addAttribute("examFee", new ExamFee());
        model.addAttribute("students", studentRepository.findAll());
        return "admin/examfee_form";
    }

    @PostMapping("/admin/examfees/save")
    public String saveExamFee(@ModelAttribute ExamFee examFee) {
        examFeeRepository.save(examFee);
        return "redirect:/admin/examfees";
    }

    @GetMapping("/student/examfees")
    public String viewOwnFees(Model model, Principal principal) {
        Long studentId = studentRepository.findByUserUsername(principal.getName()).getId();
        model.addAttribute("examFees", examFeeRepository.findByStudentId(studentId));
        return "student/examfees";
    }
    
    @GetMapping("/admin/examfees/edit/{id}")
    public String editExamFee(@PathVariable Long id, Model model) {
        ExamFee examFee = examFeeRepository.findById(id).orElse(null);
        if (examFee == null) {
            return "redirect:/admin/examfees";
        }
        model.addAttribute("examFee", examFee);
        model.addAttribute("students", studentRepository.findAll());
        return "admin/examfee_form";
    }

    @GetMapping("/admin/examfees/delete/{id}")
    public String deleteExamFee(@PathVariable Long id) {
        examFeeRepository.deleteById(id);
        return "redirect:/admin/examfees";
    }

    @GetMapping("/student/examfees/api")
    @ResponseBody
    public ResponseEntity<List<ExamFee>> getOwnFeesAPI(Principal principal) {
        Long studentId = studentRepository.findByUserUsername(principal.getName()).getId();
        return ResponseEntity.ok(examFeeRepository.findByStudentId(studentId));
    }
}
