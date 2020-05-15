package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public String newUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return "User creat amb id "+newUser.getId();
    }

    @GetMapping("/user")
    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }
}
