package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
}