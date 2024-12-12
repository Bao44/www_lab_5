package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Sử dụng JPQL để tìm kiếm jobName trong Job và compName trong Company
    @Query("SELECT j FROM Job j WHERE j.jobName LIKE %?1% OR j.company.compName LIKE %?2%")
    List<Job> findByJobNameContainingOrCompanyNameContaining(String jobName, String companyName);
}
