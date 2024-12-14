package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;
import vn.edu.iuh.fit.lab_5.backend.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SkillServices {
    @Autowired
    private SkillRepository sr;

    public Skill add(Skill skill) {
        return sr.save(skill);
    }

    public List<Skill> addMany(List<Skill> list) {
        List<Skill> results = new ArrayList<>();
        Iterator<Skill> output = sr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Skill update(Skill skill) {
        return sr.save(skill);
    }

    public void delete(Long id) throws EntityIdNotFoundException {
        sr.delete(sr.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Optional<Skill> getById(Long aLong) throws EntityIdNotFoundException {
        return Optional.of(sr.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(aLong + "")));
    }

    public Iterator<Skill> getAll() {
        return sr.findAll().iterator();
    }

    public Skill getSkillByName(String skillName) {
        return sr.findBySkillName(skillName).orElse(null);
    }
}
