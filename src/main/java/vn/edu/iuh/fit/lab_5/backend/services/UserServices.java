package vn.edu.iuh.fit.lab_5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_5.backend.models.User;
import vn.edu.iuh.fit.lab_5.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long aLong) {
        return Optional.of(userRepository.findById(aLong)).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email)).orElse(null);
    }
}
