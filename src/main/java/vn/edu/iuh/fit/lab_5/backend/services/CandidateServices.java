package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Experience;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.ExperienceRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository cr;
    @Autowired
    private CandidateSkillRepository csr;
    @Autowired
    private ExperienceRepository er;


    public Candidate add(Candidate candidate) {
        return cr.save(candidate);
    }

    public List<Candidate> addMany(List<Candidate> list) {
        List<Candidate> results = new ArrayList<>();
        Iterator<Candidate> output = cr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Candidate update(Candidate candidate) {
        return cr.save(candidate);
    }

    public void delete(Long id) throws EntityIdNotFoundException {
        cr.delete(cr.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Optional<Candidate> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(cr.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Iterator<Candidate> getAll() {
        return cr.findAll().iterator();
    }

    public Iterator<Candidate> getAll(Pageable pageable) {
        return cr.findAll(pageable).iterator();
    }

    public Candidate checkLoginAccount(String email, String password) {
        return cr.findByEmailAndPassword(email, password).orElse(null);
    }

    public List<CandidateSkill> getCandidateSkill(Long canId) {
        List<CandidateSkill> results = new ArrayList<>();
        csr.findById_Candidate_Id(canId).iterator().forEachRemaining(results::add);
        return results;
    }

    public List<Experience> getCandidateExperience(Long canId) {
        List<Experience> results = new ArrayList<>();
        er.findByCandidate_Id(canId).iterator().forEachRemaining(results::add);
        return results;
    }
}
