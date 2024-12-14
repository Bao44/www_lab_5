package vn.edu.iuh.fit.lab_5.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/admin")
    public String directToAdminPage() {
        return "admin/admin-home";
    }

    @GetMapping("/login")
    public String directToLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String directToLogout(HttpServletRequest request) {
        request.getServletContext().setAttribute("account_login", null);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String directToSignUp() {
        return "signup";
    }

    @GetMapping("/forgot-pass")
    public String directToForgotPass() {
        return "forget_pass";
    }

}
