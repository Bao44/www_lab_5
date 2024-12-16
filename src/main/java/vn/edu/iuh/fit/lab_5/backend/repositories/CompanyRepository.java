package vn.edu.iuh.fit.lab_5.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab_5.backend.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmail(String email);
}