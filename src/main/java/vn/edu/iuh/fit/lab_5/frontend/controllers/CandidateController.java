package vn.edu.iuh.fit.lab_5.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.dtos.JobDTO;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.*;
import vn.edu.iuh.fit.lab_5.frontend.models.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/candidate/detail")
public class CandidateController {

    @Autowired
    private CandidateModel cm;
    @Autowired
    private AuthenticateModel authenticateModel;
    @Autowired
    private SkillModel skillModel;
    @Autowired
    private CompanyModel companyModel;
    @Autowired
    private JobSkillModel jobSkillModel;
    @Autowired
    private JavaMailSender mailSender;

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

    @GetMapping("/profile-update/{id}/add-skill")
    public ModelAndView getAddCandidateSkill(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("add-skill");
        Candidate target = cm.getCandidateDetail(id);
        mv.addObject("skills", skillModel.getAllSkills().stream().map(Skill::getSkillName).toList());
        mv.addObject("candidate", target);
        return mv;
    }

    @PostMapping("/profile-update/{id}/add-skill")
    public ModelAndView addCandidateSkill(
            @PathVariable("id") Long candidateId,
            @RequestParam("inputSkillName") String skillName,
            @RequestParam("inputSkillLevel") String skillLevel,
            @RequestParam("inputMoreInfos") String moreInfos
    ) {
        ModelAndView mv = new ModelAndView("add-skill");
        Candidate target = cm.getCandidateDetail(candidateId);
        mv.addObject("candidate", target);

        try {
            // Gọi đến model để thêm kỹ năng
            CandidateSkill candidateSkill = new CandidateSkill();
            candidateSkill.setId(new CandidateSkillId(target, skillModel.getSkillBySkillName(skillName)));
            candidateSkill.setSkillLevel(SkillLevel.valueOf(skillLevel));
            candidateSkill.setMoreInfos(moreInfos);

            CandidateSkill addedSkill = cm.addCandidateSkill(candidateSkill);

            if (addedSkill != null) {
                mv.setViewName("profile");
                mv.addObject("candidate", target);
                mv.addObject("candidate_skills", cm.getCandidateSkill(target.getId()));
                mv.addObject("successMessage", "Thêm kỹ năng thành công");
            } else {
                mv.addObject("errorMessage", "Không thể thêm kỹ năng");
            }
        } catch (Exception e) {
            mv.addObject("skills", skillModel.getAllSkills().stream().map(Skill::getSkillName).toList());
            mv.addObject("candidate", target);
            mv.addObject("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
        }

        return mv;
    }

    @PostMapping("/profile-update/delete-skill")
    public ModelAndView deleteSkill(
            @RequestParam("candidateId") String candidateId,
            @RequestParam("skillName") String skillName
    ) {
        ModelAndView mv = new ModelAndView("profile");
        Candidate target = cm.getCandidateDetail(Long.parseLong(candidateId));
        mv.addObject("candidate", target);

        Long skillId = skillModel.getSkillBySkillName(skillName).getId();

        try {
            boolean check = cm.deleteCandidateSkill(target.getId(), skillId);
            if (!check) {
                mv.addObject("candidate_skills", cm.getCandidateSkill(Long.parseLong(candidateId)));
                mv.addObject("errorMessage", "Không thể xóa kỹ năng");
            }
            mv.addObject("candidate_skills", cm.getCandidateSkill(Long.parseLong(candidateId)));
            mv.addObject("successMessage", "Xóa kỹ năng thành công");
        } catch (Exception e) {
            mv.addObject("candidate_skills", cm.getCandidateSkill(Long.parseLong(candidateId)));
            mv.addObject("errorMessage", "Không thể xóa kỹ năng: " + e.getMessage());
        }

        return mv;
    }


    @GetMapping("/{id}/jobs-match-candidate")
    public ModelAndView getCandidatesMatchJob(@PathVariable("id") Long canId) {
        ModelAndView mv = new ModelAndView("user/match-jobs");
        Candidate candidate = cm.getCandidateDetail(canId);
        List<JobDTO> jobsDTOS = cm.getJobsMatchCandidate(canId);
        List<JobSkill> jobSkills = jobSkillModel.getAllJobSkills();
        List<CandidateSkill> candidateSkill = cm.getCandidateSkill(canId);
        mv.addObject("jobSkills", jobSkills);
        mv.addObject("jobs", jobsDTOS);
        mv.addObject("candidate", candidate);
        mv.addObject("candidateSkills", candidateSkill);
        return mv;
    }


    @PostMapping("{id}/sendEmail")
    public ModelAndView sendEmail(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("admin/candidate/candidate-detail");
        Candidate target = cm.getCandidateDetail(id);

        // Tạo nội dung email
        String subject = "Thư mời hợp tác từ Công ty ABC - Dành cho ứng viên: " + target.getFullName();
        String text = "Kính gửi " + target.getFullName() + ",\n\n" +
                "Lời đầu tiên, Công ty ABC xin gửi tới bạn lời chào trân trọng và lời cảm ơn vì đã quan tâm đến vị trí tuyển dụng tại công ty chúng tôi.\n\n" +
                "Qua quá trình xem xét hồ sơ của bạn, chúng tôi rất ấn tượng với những kỹ năng và kinh nghiệm mà bạn đã tích lũy được trong thời gian qua. " +
                "Chúng tôi tin rằng bạn là một ứng viên tiềm năng, phù hợp với yêu cầu và văn hóa làm việc của Công ty ABC.\n\n" +
                "Hiện tại, công ty chúng tôi đang tìm kiếm những nhân sự có năng lực và tâm huyết cho vị trí [Tên vị trí tuyển dụng cụ thể]. " +
                "Nếu bạn quan tâm, chúng tôi rất mong nhận được phản hồi từ bạn để có thể sắp xếp một buổi trao đổi chi tiết hơn.\n\n" +
                "Dưới đây là thông tin liên hệ của chúng tôi:\n" +
                "- Email: hrabc@gmail.com\n" +
                "- Số điện thoại: (0123) 456-789\n" +
                "- Website: www.abc.com\n\n" +
                "Rất mong nhận được phản hồi từ bạn trong thời gian sớm nhất. Nếu có bất kỳ thắc mắc nào, bạn vui lòng liên hệ với chúng tôi qua email hoặc số điện thoại trên.\n\n" +
                "Chúng tôi hy vọng sẽ có cơ hội hợp tác và làm việc cùng bạn.\n\n" +
                "Trân trọng,\n" +
                "Tran Van Teo\n" +
                "Phòng Nhân sự - Công ty ABC\n" +
                "Email: hrabc@gmail.com\n" +
                "Điện thoại: (0123) 456-789";

        // Gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(target.getEmail());
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

        mv.addObject("candidate", target);
        mv.addObject("emailSent", true);
        return mv;
    }
}
