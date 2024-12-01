package vn.edu.iuh.fit.lab_5.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home-fe")
public class HomeController {

    @GetMapping("/user")
    public String directToSearchPage() {
        return "user/search-job";
    }

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
