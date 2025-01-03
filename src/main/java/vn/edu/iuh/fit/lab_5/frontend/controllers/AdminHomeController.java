package vn.edu.iuh.fit.lab_5.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.frontend.models.AdminHomeModel;
import vn.edu.iuh.fit.lab_5.frontend.models.CandidateModel;
import vn.edu.iuh.fit.lab_5.frontend.models.CompanyModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin-fe")
public class AdminHomeController {
    @Autowired
    private AdminHomeModel ahm;
    @Autowired
    private CompanyModel cpm;
    @Autowired
    private CandidateModel cm;

    @GetMapping("/skill")
    public ModelAndView directToSkillManagement(ModelAndView mv) {
        mv.addObject("skillTypes", Arrays.stream(SkillType.values()).toList());
        mv.addObject("skills", ahm.getAllSkills());
        mv.setViewName("admin/skill");
        return mv;
    }

    @GetMapping("/candidate/{page}")
    public ModelAndView directToCandidateManagement(ModelAndView mv, @PathVariable("page") String page) {
        mv.setViewName("admin/candidate");

        List<Candidate> candidates = ahm.getAllCandidates();
        int pageSize = candidates.size() / 10;

        List<String> pages = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pages.add((i + 1) + "");
        }

        mv.addObject("candidates", cm.getCandidatesForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("pages", pages);
        return mv;
    }

    @GetMapping("/company/{page}")
    public ModelAndView directToCompanyManagement(ModelAndView mv, @PathVariable("page") String page) {
        mv.setViewName("admin/company");

        List<Company> company = cpm.getAllCompanies();
        int pageSize = company.size() / 12;

        List<String> pages = new ArrayList<>();
        for (int i = 0; i <= pageSize; ++i) {
            pages.add((i + 1) + "");
        }

        mv.addObject("companies", cpm.getCompaniesForPage(Integer.parseInt(page) == 0 ? 0 : Integer.parseInt(page) - 1));
        mv.addObject("pages", pages);

        return mv;
    }
}
