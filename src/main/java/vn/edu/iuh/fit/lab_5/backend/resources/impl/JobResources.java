package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.JobServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/job")
@Slf4j
public class JobResources implements IManagement<Job, Long> {

    @Autowired
    private JobServices js;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Job job) {
        log.info("Calling insert job");
        try {
            Job output = js.add(job);
            log.info("Insert job successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert job failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert job failed!",
                    null
            ));
        }
    }

    @Override
    public ResponseEntity<Response> insertAll(@RequestBody List<Job> list) {
        return null;
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable("id") Long aLong, @RequestBody Job job) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable("id") Long aLong) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> getById(@PathVariable("id") Long aLong) {
        log.info("Calling get Job by id = " + aLong);
        try {
            Optional<Job> out = js.getById(aLong);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get Job successfully",
                    out
            ));
        } catch (EntityIdNotFoundException e) {
            log.warn("Get Job failed for the Job id not found!");
            return ResponseEntity.ok(new Response(
                    HttpStatus.NO_CONTENT.value(),
                    "The Job id = " + aLong + " was not found!",
                    null
            ));
        } catch (Exception e) {
            log.error("Get Job failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get Job failed!",
                    null
            ));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        log.info("Calling get all jobs");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all jobs successfully",
                js.getAll()
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchJobs(@RequestParam(value = "search", required = false) String searchTerm) {
        List<Job> jobs;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // Tìm kiếm công việc theo tên hoặc tên công ty
            jobs = js.search(searchTerm, searchTerm);
        } else {
            // Nếu không tìm kiếm, lấy tất cả công việc
            jobs = (List<Job>) js.getAll();
        }

        System.out.println("Jobs after search: " + jobs.size());
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get jobs successfully",
                jobs
        ));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Response> getAll(@PathVariable("page") String pageNumber) {
        Pageable page = PageRequest.of(Integer.parseInt(pageNumber), 12);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all jobSkill successfully",
                js.getAll(page)
        ));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Response> getJobsByCompanyId(@PathVariable Long companyId) {
        log.info("Calling get Job by companyId = " + companyId);
        List<Job> jobs;
        if (companyId != null) {
            jobs = js.getJobsByCompanyId(companyId);
        } else {
            jobs = null;
        }
        System.out.println("Jobs after by companyId: " + jobs.size());
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get jobs  by companyId successfully",
                jobs
        ));
    }

    @GetMapping("/{jobId}/candidates-match-job")
    public ResponseEntity<Response> getCandidatesMatchJob(@PathVariable Long jobId) {
        log.info("Calling get candidates match job by jobId = " + jobId);
        List<CandidateDTO> candidatesDTO;
        if (jobId != null) {
            candidatesDTO = js.getCandidatesForJob(jobId);
        } else {
            candidatesDTO = null;
        }
        System.out.println("Candidates after match job: " + candidatesDTO.size());
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get candidates match job successfully",
                candidatesDTO
        ));
    }
}
