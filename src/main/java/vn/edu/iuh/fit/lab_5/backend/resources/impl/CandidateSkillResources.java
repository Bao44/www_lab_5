package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.CandidateSkillServices;

import java.util.List;

@RestController
@RequestMapping("api/candidate-skill")
@Slf4j
public class CandidateSkillResources implements IManagement<CandidateSkill, CandidateSkillId> {

    @Autowired
    private CandidateSkillServices css;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody CandidateSkill candidateSkill) {
        return null;
    }

    @PostMapping("/list")
    @Override
    public ResponseEntity<Response> insertAll(@RequestBody List<CandidateSkill> list) {
        return null;
    }

    @PutMapping
    @Override
    public ResponseEntity<Response> update(CandidateSkillId candidateSkillId, CandidateSkill candidateSkill) {
        return null;
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Response> delete(CandidateSkillId candidateSkillId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> getById(CandidateSkillId candidateSkillId) {
        return null;
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        return null;
    }
}
