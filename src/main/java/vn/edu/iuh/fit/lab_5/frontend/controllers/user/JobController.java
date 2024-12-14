package vn.edu.iuh.fit.lab_5.frontend.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.frontend.models.JobModel;

import java.util.List;

@Controller
@RequestMapping("user-fe/search-job")
public class JobController {
    @Autowired
    private JobModel jm;

    @GetMapping("/job")
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


}
