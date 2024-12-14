package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;

import java.util.Optional;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    @Query("select s from Skill s where s.skillName = ?1")
    Optional<Skill> findBySkillName(String skillName);

}