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

//    @GetMapping("/job")
//    public ModelAndView getAllJobs(ModelAndView mv) {
//        List<Job> jobs = jm.getAllJobs();
//        mv.addObject("jobs", jobs);
//        mv.setViewName("user/search-job");
//        return mv;
//    }

    // Phương thức để lấy tất cả công việc
    @GetMapping("/job")
    public ModelAndView getAllJobs(@RequestParam(value = "searchJob", required = false) String searchJob, ModelAndView mv) {
        List<Job> jobs;

        // Kiểm tra nếu có yêu cầu tìm kiếm
        if (searchJob != null && !searchJob.trim().isEmpty()) {
            // Tìm kiếm công việc theo tên job hoặc tên công ty
            jobs = jm.searchJobsByNameOrCompany(searchJob);
            System.out.println("Jobs after search: " + jobs.size()); // In ra số lượng công việc tìm thấy
        } else {
            // Nếu không tìm kiếm, lấy tất cả công việc
            jobs = jm.getAllJobs();
            System.out.println("All Jobs: " + jobs.size()); // In ra số lượng công việc
        }

        mv.addObject("jobs", jobs);
        mv.setViewName("user/search-job");
        return mv;
    }


}
