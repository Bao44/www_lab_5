package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.ids.JobSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.repositories.JobSkillRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobSkillServices {
    @Autowired
    private JobSkillRepository jsr;

    public JobSkill add(JobSkill jobSkill) {
        return jsr.save(jobSkill);
    }

    public List<JobSkill> addMany(List<JobSkill> list) {
        List<JobSkill> results = new ArrayList<>();
        Iterator<JobSkill> output = jsr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public JobSkill update(JobSkill jobSkill) {
        return jsr.save(jobSkill);
    }

    public void delete(JobSkillId jobSkillId) throws EntityIdNotFoundException {
        jsr.delete(getById(jobSkillId).orElseThrow(() -> new EntityIdNotFoundException("JobId: " + jobSkillId.getJob().getId() + " SkillId: " + jobSkillId.getSkill().getId())));
    }

    public Optional<JobSkill> getById(JobSkillId jobSkillId) throws EntityIdNotFoundException {
        return Optional.of(jsr.findById(jobSkillId).orElseThrow(() -> new EntityIdNotFoundException("JobId: " + jobSkillId.getJob().getId() + " SkillId: " + jobSkillId.getSkill().getId())));
    }

    public Iterator<JobSkill> getAll() {
        return jsr.findAll().iterator();
    }

    public List<JobSkill> getAllJobsBySkill(Long skillId) {
        return jsr.findById_Skill_Id(skillId);
    }

    public List<JobSkill> getAllSkillsByJob(Long jobId) {
        return jsr.findById_Job_Id(jobId);
    }

    public Iterator<JobSkill> getAll(Pageable pageable) {
        return jsr.findAll(pageable).iterator();
    }
}
