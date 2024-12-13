package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.dtos.AddressDTO;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Address;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Experience;
import vn.edu.iuh.fit.lab_5.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.lab_5.backend.repositories.ExperienceRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository cr;
    @Autowired
    private CandidateSkillRepository csr;
    @Autowired
    private ExperienceRepository er;
    @Autowired
    private AddressRepository ar;

    public Candidate add(Candidate candidate) {
        return cr.save(candidate);
    }

    public List<Candidate> addMany(List<Candidate> list) {
        List<Candidate> results = new ArrayList<>();
        Iterator<Candidate> output = cr.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }


    public CandidateDTO updateCandidate(CandidateDTO candidateDto) {
        Candidate candidate = cr.findById(candidateDto.getId()).orElse(null);
        if (candidate != null) {
            candidate.setFullName(candidateDto.getFullName());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setPhone(candidateDto.getPhone());

            // Assuming AddressDTO is mapped to Address entity in Candidate
            Address address = mapToAddressEntity(candidateDto.getAddress());
            candidate.setAddress(address);

            candidate.setDob(candidateDto.getDob());
            ar.save(address);
            cr.save(candidate);
            return candidateDto;
        }
        return null;
    }

    // Assuming you have this method to map AddressDTO to Address entity
    private Address mapToAddressEntity(AddressDTO addressDto) {
        // Convert AddressDTO to Address entity (you might need to adjust this logic based on your model)
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry()); // If CountryCode is being used, adapt accordingly
        address.setNumber(addressDto.getNumber());
        address.setZipcode(addressDto.getZipcode());
        return address;
    }



    public void delete(Long id) throws EntityIdNotFoundException {
        cr.delete(cr.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Optional<Candidate> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(cr.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Iterator<Candidate> getAll() {
        return cr.findAll().iterator();
    }

    public Iterator<Candidate> getAll(Pageable pageable) {
        return cr.findAll(pageable).iterator();
    }

    public Candidate checkLoginAccount(String email, String password) {
        return cr.findByEmailAndPassword(email, password).orElse(null);
    }

    public List<CandidateSkill> getCandidateSkill(Long canId) {
        List<CandidateSkill> results = new ArrayList<>();
        csr.findById_Candidate_Id(canId).iterator().forEachRemaining(results::add);
        return results;
    }

    public List<Experience> getCandidateExperience(Long canId) {
        List<Experience> results = new ArrayList<>();
        er.findByCandidate_Id(canId).iterator().forEachRemaining(results::add);
        return results;
    }
}
