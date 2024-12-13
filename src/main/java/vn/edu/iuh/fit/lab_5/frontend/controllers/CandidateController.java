package vn.edu.iuh.fit.lab_5.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.models.Address;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.frontend.models.AuthenticateModel;
import vn.edu.iuh.fit.lab_5.frontend.models.CandidateModel;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/candidate/detail")
public class CandidateController {

    @Autowired
    private CandidateModel cm;
    @Autowired
    private AuthenticateModel authenticateModel;

    @GetMapping("{id}")
    public ModelAndView getCandidateDetail(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("admin/candidate/candidate-detail");
        Candidate target = cm.getCandidateDetail(id);
        mv.addObject("candidate", target);
        mv.addObject("candidate_skills", cm.getCandidateSkill(id));
        mv.addObject("candidate_experiences", cm.getCandidateExperiences(id));
        return mv;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView getCandidateProfile(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("profile");
        Candidate target = cm.getCandidateDetail(id);
        mv.addObject("candidate", target);
        mv.addObject("candidate_skills", cm.getCandidateSkill(id));
        mv.addObject("candidate_experiences", cm.getCandidateExperiences(id));
        return mv;
    }

    @GetMapping("/profile-update/{id}")
    public ModelAndView getUpdateCandidate(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("profile-update");
        Candidate target = cm.getCandidateDetail(id);
        mv.addObject("candidate", target);
        return mv;
    }

    @PostMapping("/profile-update/{id}")
    public ModelAndView updateCandidate(
            @PathVariable("id") Long id,
            @RequestParam("inputFullName") String fullName,
            @RequestParam("inputDob") String dob,
            @RequestParam("inputEmail") String email,
            @RequestParam("inputPhone") String phone,
            @RequestParam("inputStreet") String street,
            @RequestParam("inputCity") String city,
            @RequestParam("inputCountry") String country,
            @RequestParam("inputNumber") String number,
            @RequestParam("inputZipcode") String zipcode,
            ModelAndView mv
    ) {
        mv = new ModelAndView("profile-update");
        Candidate target = cm.getCandidateDetail(id);
        mv.addObject("candidate", target);

        CandidateDTO updatedCandidate = cm.updateCandidate(id, fullName, dob, email, phone, street, city, country, number, zipcode);

        if (updatedCandidate != null) {
            mv.setViewName("profile");
            mv.addObject("candidate", updatedCandidate);
            mv.addObject("candidate_skills", cm.getCandidateSkill(id));
            mv.addObject("candidate_experiences", cm.getCandidateExperiences(id));
        } else {
            mv.setViewName("profile-update");
            mv.addObject("error", "Cập nhật thông tin không thành công");
        }
        return mv;
    }

}
