package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.repositories.CompanyRepository;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {
    @Autowired
    private CompanyRepository cr;

    public Company add(Company company) {
        return cr.save(company);
    }

    public List<Company> addMany(List<Company> list) {
        List<Company> results = new ArrayList<>();
        Iterator<Company> output = cr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Company update(Company company) {
        return cr.save(company);
    }

    public void delete(Long aLong) throws EntityIdNotFoundException {
        cr.delete(cr.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(aLong))));
    }
    public Optional<Company> getById(Long aLong) throws EntityIdNotFoundException {
        return Optional.of(cr.findById(aLong).orElseThrow(() -> new EntityIdNotFoundException(String.valueOf(aLong))));
    }

    public Iterator<Company> getAll() {
        return cr.findAll().iterator();
    }

    public Company getCompanyByEmail(String email) {
        return cr.findByEmail(email);
    }
}
