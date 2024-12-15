package vn.edu.iuh.fit.lab_5.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.models.*;
import vn.edu.iuh.fit.lab_5.frontend.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth-fe")
public class AuthenticateController {
    @Autowired
    private AuthenticateModel am;
    @Autowired
    private AuthenticateModel authenticateModel;
    @Autowired
    private SkillModel skillModel;
    @Autowired
    private JobModel jm;
    @Autowired
    private JobSkillModel jobSkillModel;
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private JobModel jobModel;

    @GetMapping("/login/{page}")
    public ModelAndView getIndex(
            @PathVariable("page") String page,
            HttpServletRequest request
    ) {
        ModelAndView mv = new ModelAndView("index");
        Candidate target = (Candidate) request.getServletContext().getAttribute("account_login");
        if (target == null) {
            mv.setViewName("login");
            return mv;
        }

        List<JobSkill> jobSkills = jobSkillModel.getAllJobSkills();
        List<CandidateSkill> candidateSkills = candidateModel.getAllCandidateSkills();

        List<Job> jobs = jm.getAllJobs();
        int pageSize = jobs.size() / 12;

        List<String> pages = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pages.add((i + 1) + "");
        }

        List<Candidate> candidates = candidateModel.getAllCandidates();
        List<String> pagesADMIN = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pagesADMIN.add((i + 1) + "");
        }

        Candidate id = candidateModel.getCandidateDetail(target.getId());
        Candidate candidate = authenticateModel.getCandidate(id.getId());
        request.getServletContext().setAttribute("candidate", candidate);
        request.getServletContext().setAttribute("candidates", candidates);
        request.getServletContext().setAttribute("candidateSkills", candidateSkills);
        request.getServletContext().setAttribute("role", target.getRole().toString());
        request.getServletContext().setAttribute("skills", skillModel.getAllSkills());
        request.getServletContext().setAttribute("jobSkills", jobSkills);
        mv.addObject("candidate", candidate);
        mv.addObject("candidates", candidateModel.getCandidatesForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("candidateSkills", candidateSkills);
        mv.addObject("role", target.getRole().toString());
        mv.addObject("account_login", target);
        mv.addObject("skills", skillModel.getAllSkills());
        mv.addObject("jobSkills", jobSkills);
        mv.addObject("jobs", jobModel.getJobForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("pages", pages);
        mv.addObject("pagesADMIN", pagesADMIN);

        return mv;
    }

    @PostMapping("/login/{page}")
    public ModelAndView checkLogin(
            @PathVariable("page") String page,
            @RequestParam("inputEmail") String email,
            @RequestParam("inputPassword") String password,
            HttpServletRequest request
    ) {
        ModelAndView mv = new ModelAndView("index");
        Candidate target = am.checkLogin(email, password);

        List<JobSkill> jobSkills = jobSkillModel.getAllJobSkills();
        List<CandidateSkill> candidateSkills = candidateModel.getAllCandidateSkills();

        List<Job> jobs = jm.getAllJobs();
        int pageSize = jobs.size() / 12;

        List<String> pages = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pages.add((i + 1) + "");
        }
        List<Candidate> candidates = candidateModel.getAllCandidates();
        List<String> pagesADMIN = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pagesADMIN.add((i + 1) + "");
        }

        if (target != null) {
            Candidate id = candidateModel.getCandidateDetail(target.getId());
            Candidate candidate = authenticateModel.getCandidate(id.getId());
            request.getServletContext().setAttribute("candidate", candidate);
            mv.addObject("candidate", candidate);
        }
        request.getServletContext().setAttribute("candidateSkills", candidateSkills);
        request.getServletContext().setAttribute("account_login", target);
        request.getServletContext().setAttribute("role", target.getRole().toString());
        request.getServletContext().setAttribute("skills", skillModel.getAllSkills());
        request.getServletContext().setAttribute("jobSkills", jobSkills);
        mv.addObject("role", target.getRole().toString());
        mv.addObject("account_login", target);
        mv.addObject("skills", skillModel.getAllSkills());
        mv.addObject("jobSkills", jobSkills);
        mv.addObject("jobs", jobModel.getJobForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("pages", pages);
        mv.addObject("pagesADMIN", pagesADMIN);
        mv.addObject("candidates", candidateModel.getCandidatesForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("candidateSkills", candidateSkills);
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
