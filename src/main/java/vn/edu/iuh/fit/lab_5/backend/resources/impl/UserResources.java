package vn.edu.iuh.fit.lab_5.backend.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.User;
import vn.edu.iuh.fit.lab_5.backend.resources.IManagement;
import vn.edu.iuh.fit.lab_5.backend.services.UserServices;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResources implements IManagement<User, Long> {
    @Autowired
    private UserServices userService;
    @Override
    public ResponseEntity<Response> insert(User user) {
        return null;
    }

    @Override
    public ResponseEntity<Response> insertAll(List<User> list) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(Long aLong, User user) {
        return null;
    }

    @Override
    public ResponseEntity<Response> delete(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Response> getById(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Response> getAll() {
        return null;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(
                new Response(
                        HttpStatus.OK.value(),
                        "Get user by email success!",
                        userService.findByEmail(email)
                )
        );
    }
}
