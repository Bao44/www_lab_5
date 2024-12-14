package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.CandidateSkillServices;

import java.util.List;

@RestController
@RequestMapping("api/candidate-skill")
@Slf4j
public class CandidateSkillResources implements IManagement<CandidateSkill, CandidateSkillId> {

    @Autowired
    private CandidateSkillServices css;

    @PostMapping("/add")
    public ResponseEntity<?> addCandidateSkill(@RequestBody CandidateSkill candidateSkill) {
        try {
            CandidateSkill addSkill = css.add(candidateSkill);
            return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Thêm kỹ năng thành công", addSkill));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.OK.value(), "Thêm kỹ năng thất bại", null));
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody CandidateSkill candidateSkill) {
        log.info("Calling insert skill");
        try {
            CandidateSkill output = css.add(candidateSkill);
            log.info("Insert candidateSkill successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert candidateSkill successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert candidateSkill failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert candidateSkill failed!",
                    null
            ));
        }
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

    @GetMapping("/remove-candidate-skill")
    public ResponseEntity<Boolean> removeCandidateSkill(@RequestParam Long candidateId, @RequestParam Long skillId) throws Exception {
        try {
            return ResponseEntity.ok(css.removeCandidateSkill(candidateId, skillId));
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
}
