package vn.edu.iuh.fit.lab_5.frontend.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.frontend.models.CandidateModel;
import vn.edu.iuh.fit.lab_5.frontend.models.CompanyModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;

import java.util.List;

@Controller
@RequestMapping("jobs")
public class JobController {
    @Autowired
    private JobModel jm;
    @Autowired
    private CandidateModel cm;
    @Autowired
    private CompanyModel companyModel;
    @Autowired
    private JobSkillModel jobSkillModel;

    @GetMapping("/search-job/job")
    public ModelAndView getAllJobs(@RequestParam(value = "search", required = false) String searchJob, ModelAndView mv) {
        List<Job> jobs;
        if (searchJob != null && !searchJob.trim().isEmpty()) {
            jobs = jm.searchJobsByNameOrCompany(searchJob);
        } else {
            jobs = jm.getAllJobs();
        }

        mv.addObject("jobs", jobs);
        mv.setViewName("user/search-job");
        return mv;
    }

    @GetMapping("/{id}/candidates-match-job")
    public ModelAndView getCandidatesMatchJob(@PathVariable("id") Long jobId) {
        ModelAndView mv = new ModelAndView("admin/company/match-candidate");
        Job job = jm.getJobById(jobId);
        Company target = companyModel.getCompanyById(job.getCompany().getId());
        List<JobSkill> jobSkills = jobSkillModel.getAllJobSkills();
        List<CandidateDTO> candidateDTOS = jm.getCandidatesMatchJob(jobId);
        mv.addObject("company", target);
        mv.addObject("job", job);
        mv.addObject("jobSkills", jobSkills);
        mv.addObject("candidates", candidateDTOS);
        return mv;
    }
}
