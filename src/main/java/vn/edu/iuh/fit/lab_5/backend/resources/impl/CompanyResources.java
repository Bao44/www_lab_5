package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.CompanyServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/company")
@Slf4j
public class CompanyResources implements IManagement<Company, Long> {

    @Autowired
    private CompanyServices cs;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Company company) {
        return null;
    }

    @Override
    public ResponseEntity<Response> insertAll(@RequestBody List<Company> list) {
        return null;
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable("id") Long aLong, @RequestBody Company company) {
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
        log.info("Calling get company by id = " + aLong);
        try {
            Optional<Company> out = cs.getById(aLong);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get company successfully",
                    out
            ));
        } catch (EntityIdNotFoundException e) {
            log.warn("Get company failed for the company id not found!");
            return ResponseEntity.ok(new Response(
                    HttpStatus.NO_CONTENT.value(),
                    "The company id = " + aLong + " was not found!",
                    null
            ));
        } catch (Exception e) {
            log.error("Get company failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get company failed!",
                    null
            ));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        log.info("Calling get all companies");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all companies successfully",
                cs.getAll()
        ));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Response> getAll(@PathVariable("page") String pageNumber) {
        Pageable page = PageRequest.of(Integer.parseInt(pageNumber), 12);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all candidates successfully",
                cs.getAll(page)
        ));
    }
}