package vn.edu.iuh.fit.lab_5.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.models.Address;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.frontend.models.AuthenticateModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;
import vn.edu.iuh.fit.lab_5.frontend.models.SkillModel;

import java.time.LocalDate;

@Controller
@RequestMapping("/auth-fe")
public class AuthenticateController {
    @Autowired
    private AuthenticateModel am;
    @Autowired
    private AuthenticateModel authenticateModel;
    @Autowired
    private SkillModel skillModel;

    @PostMapping("/login")
    public ModelAndView checkLogin(
            @RequestParam("inputEmail") String email,
            @RequestParam("inputPassword") String password,
            HttpServletRequest request
    ) {
        ModelAndView mv = new ModelAndView("index");
        Candidate target = am.checkLogin(email, password);
        request.getServletContext().setAttribute("account_login", target);
        request.getServletContext().setAttribute("role", target.getRole().toString());
        request.getServletContext().setAttribute("skills", skillModel.getAllSkills());
        mv.addObject("role", target.getRole().toString());
        mv.addObject("account_login", target);
        mv.addObject("skills", skillModel.getAllSkills());
        return mv;
    }

    @PostMapping("/signup")
    public ModelAndView signUpAccount(
            @RequestParam("inputFullName") String fullName,
            @RequestParam("inputEmail") String email,
            @RequestParam("inputPassword") String password,
            @RequestParam("inputPhone") String phone,
            @RequestParam("inputDob") String dob,
            @RequestParam("inputCountry") String country,
            @RequestParam("inputCity") String city,
            @RequestParam("inputStreet") String street,
            @RequestParam("inputNumber") String number,
            @RequestParam("inputZipcode") String zipCode,
            ModelAndView mv
    ) {
        String[] splitDate = dob.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));

        Address address = new Address(street, city, Short.parseShort(country), number, zipCode);

        Candidate candidate = new Candidate(date, email, fullName, phone, password, address);

        Address addressResult = authenticateModel.registerAddress(address);
        candidate.setAddress(addressResult);

        boolean result = authenticateModel.signUp(candidate);

        if (result) {
            mv.addObject("status", "Sign up successfully");
            mv.setViewName("login");
        } else {
            mv.addObject("status", "Sign up failed");
            mv.setViewName("signup");
        }
        return mv;
    }
}
