package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.ids.JobSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.JobSkillServices;

import java.util.List;

@RestController
@RequestMapping("api/job-skill")
@Slf4j
public class JobSkillResources implements IManagement<JobSkill, JobSkillId> {

    @Autowired
    private JobSkillServices jss;

    @PostMapping("/add")
    public ResponseEntity<?> addJobSkill(@RequestBody JobSkill jobSkill) {
        log.info("Calling insert job-skill");
        try {
            JobSkill output = jss.add(jobSkill);
            log.info("Insert skill successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job-skill successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert job-skill failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job-skill failed!",
                    null
            ));
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody JobSkill jobSkill) {
        log.info("Calling insert job-skill");
        try {
            JobSkill output = jss.add(jobSkill);
            log.info("Insert skill successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job-skill successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert job-skill failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job-skill failed!",
                    null
            ));
        }
    }

    @PostMapping("/list")
    @Override
    public ResponseEntity<Response> insertAll(@RequestBody List<JobSkill> list) {
        return null;
    }

    @PutMapping
    @Override
    public ResponseEntity<Response> update(JobSkillId jobSkillId, JobSkill jobSkill) {
        return null;
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Response> delete(JobSkillId jobSkillId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> getById(JobSkillId jobSkillId) {
        return null;
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all job-skill successfully",
                jss.getAll()
        ));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Response> getAll(@PathVariable("page") String pageNumber) {
        Pageable page = PageRequest.of(Integer.parseInt(pageNumber), 20);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all jobSkill successfully",
                jss.getAll(page)
        ));
    }

    @GetMapping("/jobs/{skillId}")
    public ResponseEntity<Response> getAllJobsBySkill(@PathVariable("skillId") Long skillId) {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all jobs by skill id",
                jss.getAllJobsBySkill(skillId)
        ));
    }

    @GetMapping("/skills/{jobId}")
    public ResponseEntity<Response> getAllSkillsByJob(@PathVariable("jobId") Long jobId) {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all skills by job id",
                jss.getAllSkillsByJob(jobId)
        ));
    }
}