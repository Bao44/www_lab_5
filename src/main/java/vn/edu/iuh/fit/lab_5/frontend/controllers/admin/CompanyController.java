package vn.edu.iuh.fit.lab_5.frontend.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.frontend.models.CompanyModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;

import java.util.List;

@Controller
@RequestMapping("company/detail")
public class CompanyController {
    @Autowired
    private CompanyModel companyModel;
    @Autowired
    private JobModel jobModel;
    @Autowired
    private JobSkillModel jobSkillModel;

    @GetMapping("{id}")
    public ModelAndView getCompanyDetail(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("admin/company/company-detail");
        List<JobSkill> jobSkills = jobSkillModel.getAllJobSkills();
        Company target = companyModel.getCompanyById(id);
        List<Job> jobs = jobModel.getJobsByCompanyId(id);
        mv.addObject("company", target);
        mv.addObject("jobs", jobs);
        mv.addObject("jobSkills", jobSkills);
        return mv;
    }
}
