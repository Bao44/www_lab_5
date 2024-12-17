package vn.edu.iuh.fit.lab_5.frontend.controllers.admin;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;
import vn.edu.iuh.fit.lab_5.backend.ids.JobSkillId;
import vn.edu.iuh.fit.lab_5.frontend.models.CompanyModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobModel;
import vn.edu.iuh.fit.lab_5.frontend.models.JobSkillModel;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.frontend.models.SkillModel;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin-fe/job-skill")
public class AddJobSkillController {

    @Autowired
    private JobSkillModel jsm;
    @Autowired
    private JobModel jm;
    @Autowired
    private SkillModel sm;
    @Autowired
    private CompanyModel cm;

    @GetMapping("/addJobSkill")
    public ModelAndView getAddJobSkill(ModelAndView mv) {
        mv.setViewName("admin/job");
        mv.addObject("companies", cm.getAllCompanies());
        mv.addObject("skills", sm.getAllSkills());
        return mv;
    }

    @PostMapping("/addJobSkill")
    public ModelAndView addJobSkill(
            @RequestParam("jobName") String jobName,
            @RequestParam("jobDesc") String jobDesc,
            @RequestParam("compEmail") String compEmail,
            @RequestParam("skillIds[]") Long[] skillIds,
            @RequestParam("skillLevels") String skillLevels,
            ModelAndView mv
    ) {
        List<Company> companies = cm.getAllCompanies();
        List<Skill> skills = sm.getAllSkills();
        Company company = null;

        // Tìm công ty theo email
        for (Company c : companies) {
            if (c.getEmail().equals(compEmail)) {
                company = c;
                break;
            }
        }

        if (company == null) {
            mv.setViewName("admin/job");
            mv.addObject("error", "Company with the provided email does not exist.");
            mv.addObject("skills", sm.getAllSkills());
            return mv;
        }

        // Tạo và lưu công việc
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDesc(jobDesc);
        job.setCompany(company);
        job = jm.addJob(job);

        // Danh sách JobSkill để lưu vào database
        List<JobSkill> jobSkills = new ArrayList<>();

        // Lặp qua từng skillId để tạo JobSkill
        for (int i = 0; i < skillIds.length; i++) {
            Skill skill = null;

            // Tìm skill theo id
            for (Skill s : skills) {
                if (s.getId().equals(skillIds[i])) {
                    skill = s;
                    break;
                }
            }

            if (skill != null) {
                // Tạo một JobSkill mới cho mỗi skill
                JobSkill jobSkill = new JobSkill();
                JobSkillId jobSkillId = new JobSkillId();  // Khởi tạo JobSkillId mới cho mỗi vòng lặp

                jobSkillId.setJob(job);  // Thiết lập job cho JobSkillId
                jobSkillId.setSkill(skill);  // Thiết lập skill cho JobSkillId

                jobSkill.setId(jobSkillId);  // Gán JobSkillId cho JobSkill

                // Ánh xạ từ chuỗi skillLevels sang SkillLevel
                try {
                    jobSkill.setSkillLevel(SkillLevel.fromString(skillLevels));  // Thiết lập level cho Skill
                } catch (IllegalArgumentException e) {
                    mv.setViewName("admin/job");
                    mv.addObject("error", "Invalid skill level: " + skillLevels);
                    mv.addObject("skills", sm.getAllSkills());
                    return mv;
                }

                // Thêm JobSkill vào danh sách
                jobSkills.add(jobSkill);
            }
        }

        // Lưu tất cả các JobSkill
        for (JobSkill js : jobSkills) {
            jsm.addJobSkills(js);
        }

        mv.setViewName("admin/job");
        mv.addObject("success", "Job skill added successfully.");
        return mv;
    }
}
