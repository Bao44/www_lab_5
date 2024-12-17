package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.JobRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServices {
    @Autowired
    private JobRepository jr;
    @Autowired
    CandidateRepository cr;

    public Job add(Job job) {
        return jr.save(job);
    }

    public List<Job> addMany(List<Job> list) {
        List<Job> results = new ArrayList<>();
        Iterator<Job> output = jr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Job update(Job job) {
        return jr.save(job);
    }

    public void delete(Long aLong) throws EntityIdNotFoundException {
        jr.delete(jr.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(aLong))));
    }

    public Optional<Job> getById(Long aLong) throws EntityIdNotFoundException {
        return Optional.of(jr.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(aLong))));
    }

    public Iterator<Job> getAll() {
        return jr.findAll().iterator();
    }

    public List<Job> search(String jobName, String companyName) {
        return jr.findByJobNameContainingOrCompanyNameContaining(jobName, companyName);
    }

    public Iterator<Job> getAll(Pageable pageable) {
        return jr.findAll(pageable).iterator();
    }

    public List<Job> getJobsByCompanyId(Long companyId) {
        return jr.findJobsByCompanyId(companyId);
    }

    public List<CandidateDTO> getCandidatesForJob(Long jobId) {
        List<Object[]> results = cr.findCandidatesByJobSkills(jobId);
        List<CandidateDTO> candidates = new ArrayList<>();

        for (Object[] result : results) {
            Long id = (Long) result[0];
            String fullName = (String) result[1];
            String email = (String) result[2];
            Long matchingSkills = (Long) result[3];

            Object matchingPercentageObj = result[4];
            Double matchingPercentage = null;

            if (matchingPercentageObj instanceof BigDecimal) {
                matchingPercentage = ((BigDecimal) matchingPercentageObj).doubleValue();
            } else if (matchingPercentageObj instanceof Double) {
                matchingPercentage = (Double) matchingPercentageObj;
            }

            CandidateDTO candidateDTO = new CandidateDTO(id, email, fullName, matchingSkills, matchingPercentage);
            candidates.add(candidateDTO);
        }

        return candidates;
    }
}
