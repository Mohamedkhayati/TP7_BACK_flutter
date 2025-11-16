// src/main/java/org/isetn/RestControllers/UserController.java
package org.isetn.RestControllers;

import org.isetn.entities.User;
import org.isetn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User found = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (found != null) {
            return ResponseEntity.ok(found);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}