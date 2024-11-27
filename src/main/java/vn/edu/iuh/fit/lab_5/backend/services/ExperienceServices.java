package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Experience;
import vn.edu.iuh.fit.lab_5.backend.repositories.ExperienceRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServices {
    @Autowired
    private ExperienceRepository er;

    public Experience add(Experience experience) {
        return er.save(experience);
    }

    public List<Experience> addMany(List<Experience> list) {
        List<Experience> results = new ArrayList<>();
        Iterator<Experience> output = er.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Experience update(Experience experience) {
        return er.save(experience);
    }

    public void delete(Long aLong) throws EntityIdNotFoundException {
        er.delete(er.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(aLong))));
    }

    public Optional<Experience> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(er.findById(id).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(id))));
    }

    public Iterator<Experience> getAll() {
        return er.findAll().iterator();
    }
}
