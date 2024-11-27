package vn.edu.iuh.fit.lab_5.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;
import vn.edu.iuh.fit.lab_5.frontend.models.AdminHomeModel;

import java.util.Arrays;

@Controller
@RequestMapping("admin-fe")
public class AdminHomeController {
    @Autowired
    private AdminHomeModel ahm;

    @GetMapping("/skill")
    public ModelAndView directToSkillManagement(ModelAndView mv) {
        mv.addObject("skillTytes", Arrays.stream(SkillType.values()).toList());
        mv.addObject("skills", ahm.getAllSkills());
        mv.setViewName("admin/skill");
        return mv;
    }
}
