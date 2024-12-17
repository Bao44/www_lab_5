package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT c.id, c.full_name, c.email, " +
            "COUNT(DISTINCT cs.skill_id) AS matching_skills, " +
            "COUNT(DISTINCT cs.skill_id) * 100 / ( " +
            "SELECT COUNT(DISTINCT js2.skill_id) " +
            "FROM job_skill js2 " +
            "WHERE js2.job_id = :jobId) AS matching_percentage " +
            "FROM Candidate c " +
            "JOIN candidate_skill cs ON c.id = cs.can_id " +
            "JOIN job_skill js ON cs.skill_id = js.skill_id " +
            "WHERE js.job_id = :jobId " +
            "AND cs.skill_level >= js.skill_level " +
            "GROUP BY c.id " +
            "HAVING matching_percentage > 0 " +
            "ORDER BY matching_percentage DESC",
            nativeQuery = true)  // sử dụng nativeQuery để chạy câu lệnh SQL gốc
    List<Object[]> findCandidatesByJobSkills(@Param("jobId") Long jobId);  // Truyền job_id làm tham số
}
