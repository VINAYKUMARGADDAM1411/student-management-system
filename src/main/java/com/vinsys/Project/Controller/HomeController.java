package com.vinsys.Project.Controller;

import com.vinsys.Project.Service.UserService;
import com.vinsys.Project.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "role", required = false) String role, 
                            Model model) {
        model.addAttribute("role", role);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login?role=student";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/admin-register")
    public String registerAdminPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/admin-register";
    }
    @PostMapping("/admin-register")
    public String registerAdmin(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerAdmin(user);
            return "redirect:/login?role=admin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/admin-register";
        }
    }
    @GetMapping("/role-redirect")
    public String roleRedirect(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
            return "redirect:/student/dashboard";
        }
        return "redirect:/home";
    }
}
