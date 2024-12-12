package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.JobServices;

import java.util.List;

@RestController
@RequestMapping("api/job")
@Slf4j
public class JobResources implements IManagement<Job,Long> {

    @Autowired
    private JobServices js;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Job job) {
        return null;
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
        return null;
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

}
