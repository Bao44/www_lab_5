package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    @Modifying
    @Query("DELETE FROM CandidateSkill c WHERE c.id.candidate.id = ?1 AND c.id.skill.id = ?2")
    void removeByCandidateIdAndSkillId(Long candidateId, Long skillId);
    @Query("SELECT c FROM CandidateSkill c WHERE c.id.candidate.id = ?1 AND c.id.skill.id = ?2")
    Optional<CandidateSkill> findByCandidateIdAndSkillId(Long candidateId, Long skillId);
    List<CandidateSkill> findById_Candidate_Id(Long id);
}