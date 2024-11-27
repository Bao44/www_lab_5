package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateSkillRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillServices {
    @Autowired
    private CandidateSkillRepository csr;

    public CandidateSkill add(CandidateSkill candidateSkill) {
        return csr.save(candidateSkill);
    }

    public List<CandidateSkill> addMany(List<CandidateSkill> list) {
        List<CandidateSkill> results = new ArrayList<>();
        Iterator<CandidateSkill> output = csr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public CandidateSkill update(CandidateSkill candidateSkill) {
        return csr.save(candidateSkill);
    }

    public void delete(CandidateSkillId id) throws EntityIdNotFoundException {
        csr.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException("skillId: " + id.getCandidate() + ", candidateId: " + id.getCandidate())));
    }

    public Optional<CandidateSkill> getById(CandidateSkillId id) throws EntityIdNotFoundException {
        return Optional.of(csr.findById(id).orElseThrow(() -> new EntityIdNotFoundException("skillId: " + id.getSkill() + ", candidateId: " + id.getSkill())));
    }

    public Iterator<CandidateSkill> getAll() {
        return csr.findAll().iterator();
    }
}
