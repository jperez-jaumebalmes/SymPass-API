package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.LoginForm;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> logInUser(@RequestBody LoginForm loginForm){
        HttpHeaders responseHeaders = new HttpHeaders();
        Optional<User> optUser = userRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if(optUser.isPresent()){
             responseHeaders.set("Allow-SymPass-Access","2");
            return ResponseEntity.status(200).body(2);
        }
        optUser = userRepository.findByEmail(loginForm.getEmail());
        if(optUser.isPresent()){
            responseHeaders.set("Allow-SymPass-Access","1");
            return ResponseEntity.status(406).body(1);
        }else{
            responseHeaders.set("Allow-SymPass-Access","0");
            return ResponseEntity.status(401).body(0);
        }

    }

    @GetMapping("/user")
    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }
}
