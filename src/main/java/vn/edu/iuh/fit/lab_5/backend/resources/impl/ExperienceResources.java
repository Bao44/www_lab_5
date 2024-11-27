package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.models.Experience;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.ExperienceServices;

import java.util.List;

@RestController
@RequestMapping("api/experience")
@Slf4j
public class ExperienceResources implements IManagement<Experience, Long> {

    @Autowired
    private ExperienceServices es;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Experience experience) {
        log.info("Calling insert experience");
        try {
            Experience output = es.add(experience);
            log.info("Insert experience successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert experience successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert experience failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert experience failed!",
                    null
            ));
        }
    }

    @PostMapping("/list")
    @Override
    public ResponseEntity<Response> insertAll(@RequestBody List<Experience> list) {
        log.info("Calling insert experience");
        try {
            List<Experience> output = es.addMany(list);
            log.info("Insert experience successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert experience successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert experience failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert experience failed!",
                    null
            ));
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable("id") Long aLong, Experience experience) {
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
        return null;
    }
}