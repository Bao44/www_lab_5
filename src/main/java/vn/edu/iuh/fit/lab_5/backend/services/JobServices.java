package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.repositories.JobRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServices {
    @Autowired
    private JobRepository jr;

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
}
