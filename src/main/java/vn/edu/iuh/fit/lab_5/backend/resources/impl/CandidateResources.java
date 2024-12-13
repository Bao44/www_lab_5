package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateAccountDTO;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.CandidateServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/candidate")
@Slf4j
public class CandidateResources implements IManagement<Candidate, Long> {

    @Autowired
    private CandidateServices cs;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Candidate candidate) {
        log.info("Calling insert candidate");
        try {
            Candidate output = cs.add(candidate);
            log.info("Insert candidate successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert candidate successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert candidate failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert candidate failed!",
                    null
            ));
        }
    }

    @Override
    public ResponseEntity<Response> insertAll(List<Candidate> list) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(Long aLong, Candidate candidate) {
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<CandidateDTO> updateCandidate(@RequestBody CandidateDTO candidate) {
        try {
            CandidateDTO updatedCandidate = cs.updateCandidate(candidate);
            if (updatedCandidate != null) {
                return ResponseEntity.ok(updatedCandidate);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error: " + e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable("id") Long aLong) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> getById(@PathVariable("id") Long aLong) {
        log.info("Calling get candidate by id = " + aLong);
        try {
            Optional<Candidate> opCan = cs.getById(aLong);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get candidate successfully",
                    opCan.get()
            ));
        } catch (EntityIdNotFoundException e) {
            log.warn("Get candidate failed for the candidate id not found!");
            return ResponseEntity.ok(new Response(
                    HttpStatus.NO_CONTENT.value(),
                    "The candidate id = " + aLong + " was not found!",
                    null
            ));
        } catch (Exception e) {
            log.error("Get candidate failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get candidate failed!",
                    null
            ));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all candidates successfully",
                cs.getAll()
        ));
    }

    @PostMapping("login")
    public ResponseEntity<Response> checkLoginAccount(@RequestBody CandidateAccountDTO caDto) {
        log.info("Calling check login account");
        String getEmail = caDto.getEmail();
        String getPassword = caDto.getPassword();

        try {
            Candidate output = cs.checkLoginAccount(getEmail, getPassword);
            if (output != null) {
                log.info("Check login account successfully");
                return ResponseEntity.ok(new Response(
                        HttpStatus.OK.value(),
                        "Check login account successfully",
                        output
                ));
            } else {
                log.warn("Check login account failed for the email or password is incorrect!");
                return ResponseEntity.ok(new Response(
                        HttpStatus.NO_CONTENT.value(),
                        "The email or password is incorrect!",
                        null
                ));
            }
        } catch (Exception e) {
            log.error("Check login account failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Check login account failed!",
                    null
            ));
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Response> getAll(@PathVariable("page") String pageNumber) {
        Pageable page = PageRequest.of(Integer.parseInt(pageNumber), 10);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all candidates successfully",
                cs.getAll(page)
        ));
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<Response> getCandidateSkills(@PathVariable("id") Long canId) {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all candidate skills successfully",
                cs.getCandidateSkill(canId)
        ));
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<Response> getCandidateExperience(@PathVariable("id") Long canId) {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all candidate experiences successfully",
                cs.getCandidateExperience(canId)
        ));
    }
}
