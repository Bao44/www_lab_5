package vn.edu.iuh.fit.lab_5.frontend.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.frontend.models.SkillModel;

@Controller
@RequestMapping("/admin-fe/skill")
public class SkillController {

    @Autowired
    private SkillModel sm;

    @PostMapping
    public ModelAndView addSkill(
            @RequestParam("inputSkillName") String skillName,
            @RequestParam("inputSkillDescription") String skillDescription,
            @RequestParam("inputSkillType") String skillType,
            ModelAndView mv
    ) {
        mv.setViewName("redirect:/admin-fe/skill");

        Skill skill = new Skill(skillDescription, skillName, SkillType.valueOf(skillType));
        sm.addSkill(skill);
        return mv;
    }
}