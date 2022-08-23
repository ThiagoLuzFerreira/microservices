package com.thiago.hruser.resources;

import com.thiago.hruser.entities.User;
import com.thiago.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){

        Optional<User> obj = repository.findById(id);
        return ResponseEntity.ok().body(obj.get());
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(repository.findByEmail(email));
    }
}
