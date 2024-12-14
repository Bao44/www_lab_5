package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.SkillServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/skill")
@Slf4j
public class SkillResources implements IManagement<Skill, Long> {

    @Autowired
    private SkillServices ss;

    @PostMapping
    @Override
    public ResponseEntity<Response> insert(@RequestBody Skill skill) {
        log.info("Calling insert skill");
        try {
            Skill output = ss.add(skill);
            log.info("Insert skill successfully");
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert skill successfully",
                    output
            ));
        } catch (Exception e) {
            log.error("Insert skill failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Insert skill failed!",
                    null
            ));
        }
    }

    @Override
    public ResponseEntity<Response> insertAll(List<Skill> list) {
        return null;
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable("id") Long aLong, @RequestBody Skill skill) {
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
        log.info("Calling get skill by id = " + aLong);
        try {
            Optional<Skill> opCan = ss.getById(aLong);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get skill successfully",
                    opCan.get()
            ));
        } catch (EntityIdNotFoundException e) {
            log.warn("Get skill failed for the skill id not found!");
            return ResponseEntity.ok(new Response(
                    HttpStatus.NO_CONTENT.value(),
                    "The skill id = " + aLong + " was not found!",
                    null
            ));
        } catch (Exception e) {
            log.error("Get skill failed");
            log.error("Error: ", e);
            return ResponseEntity.ok(new Response(
                    HttpStatus.OK.value(),
                    "Get skill failed!",
                    null
            ));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all skill successfully",
                ss.getAll()
        ));
    }

    @PostMapping("/skill-name")
    public ResponseEntity<Response> getSkillByName(@RequestBody String skillName) {
        Skill result = ss.getSkillByName(skillName);
        return ResponseEntity.ok(new Response(
                result != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(),
                result != null ? "Get skill by name successfully" : "Skill name not found",
                result
        ));
    }
}