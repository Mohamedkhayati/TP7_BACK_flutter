package org.isetn.RestControllers;

import org.isetn.entities.Formation;
import org.isetn.entities.User;
import org.isetn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User Register(@RequestBody User user) {
        return userRepository.save(user);
    }
	/*
	 * @PostMapping("/login") public User Login(@RequestBody User user) { User
	 * oldUSer = userRepository.findByEmailAndPassword(user.getEmail(),
	 * user.getPassword()); return oldUSer; }
	 */
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedInUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    
    
}
