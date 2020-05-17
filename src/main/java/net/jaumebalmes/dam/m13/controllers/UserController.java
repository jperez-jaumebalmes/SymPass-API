package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.LoginForm;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public String newUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return "User creat amb id "+newUser.getId();
    }

    @PostMapping("/login")
    public int logInUser(@RequestBody LoginForm loginForm){
        Optional<User> optUser = userRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if(optUser.isPresent()){
            return 2;
        }
        optUser = userRepository.findByEmail(loginForm.getEmail());
        if(optUser.isPresent()){
            return 1;
        }else{
            return 0;
        }
    }

    @GetMapping("/user")
    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }
}
