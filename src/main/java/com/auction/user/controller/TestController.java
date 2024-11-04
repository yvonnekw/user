package com.auction.user.controller;


import com.auction.user.model.Users;
import com.auction.user.repostory.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-api/users")
@Profile("test")
public class TestController {

    @Autowired
    private UsersRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetDatabase() {
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
