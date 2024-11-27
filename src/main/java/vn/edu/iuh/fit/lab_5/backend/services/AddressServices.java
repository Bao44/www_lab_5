package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.exceptions.EntityIdNotFoundException;
import vn.edu.iuh.fit.lab_5.backend.models.Address;
import vn.edu.iuh.fit.lab_5.backend.repositories.AddressRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository ar;

    public Address add(Address address) {
        return ar.save(address);
    }

    public List<Address> addMany(List<Address> list) {
        List<Address> results = new ArrayList<>();
        Iterator<Address> output = ar.saveAll(list).iterator();
        output.forEachRemaining(results::add);
        return results;
    }

    public Address update(Address address) {
        return ar.save(address);
    }

    public void delete(Long id) throws EntityIdNotFoundException {
        ar.delete(getById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Optional<Address> getById(Long id) throws EntityIdNotFoundException {
        return Optional.of(ar.findById(id).orElseThrow(() -> new EntityIdNotFoundException(id + "")));
    }

    public Iterator<Address> getAll() {
        return ar.findAll().iterator();
    }
}