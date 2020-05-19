package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.PasswordRepository;
import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.Password;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class PasswordController {
    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/password/{userid}")
    public String newPassword(@RequestBody Password newPassword,@PathVariable("userid") long userid) {
        Optional<User> userOptional = userRepository.findById(userid);
        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }else{
            return "User not found";
        }
        newPassword.setUser(user);
        //newPassword.setLastModification(OffsetDateTime.now());
        passwordRepository.save(newPassword);
        return "Password creada amb id "+newPassword.getId();
    }

    @GetMapping("/password/{userid}")
    public List<Password> getPasswordListByUser(@PathVariable("userid") long userid) {
        Optional<User> userOptional = userRepository.findById(userid);
        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }else{
            return null;
        }
        return passwordRepository.findAllByUser(user);
    }

    //@GetMapping("/password")
    //public Iterable<Password> getPasswordList(){
    //    return passwordRepository.findAll();
    //}


}
