package vn.edu.iuh.fit.lab_5.frontend.controllers.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.enums.CandidateRole;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;

import java.util.List;

@Controller
@RequestMapping("/user-fe/job-skill/detail")
public class JobSkillController {

    @Autowired
    private JobSkillModel jsm;

    @GetMapping("/skills/{id}")
    public ModelAndView getAllJobsBySkill(@PathVariable("id") String jobId, ModelAndView mv, HttpServletRequest request) {
        List<JobSkill> jobSkills = jsm.getJobSkillsDetail(Long.parseLong(jobId));
        mv.addObject("jobSkills", jobSkills);
        mv.setViewName("user/job/job-detail");
        return mv;
    }
}
