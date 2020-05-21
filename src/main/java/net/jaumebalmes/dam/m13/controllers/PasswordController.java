package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.PasswordRepository;
import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.Password;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
        newPassword.setLastModification(OffsetDateTime.now());
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

    @PutMapping("/password/{passId}")
    public ResponseEntity<Integer> modifyPassword(@RequestBody Password modPassword, @PathVariable("passId") long passId){
        Optional<Password> optPassword = passwordRepository.findById(passId);
        Password oldPassword;
        if(optPassword.isPresent()){
            oldPassword = optPassword.get();
        }else{
            return ResponseEntity.status(404).body(0);
        }

        oldPassword = updateParams(oldPassword,modPassword);
        passwordRepository.save(oldPassword);

        return ResponseEntity.status(200).body(1);
    }

    private Password updateParams(Password oldPassword, Password modPassword) {
        oldPassword.setDescription(modPassword.getDescription());
        oldPassword.setIcon(modPassword.getDescription());
        oldPassword.setUsername(modPassword.getUsername());
        oldPassword.setLink(modPassword.getLink());
        oldPassword.setPassword(modPassword.getPassword());
        oldPassword.setTitle(modPassword.getTitle());
        oldPassword.setType(modPassword.getType());

        return oldPassword;
    }

    @DeleteMapping("/password/{passId}")
    public ResponseEntity<Integer> deletePassword(@PathVariable("passId") long passId){
        Optional<Password> optionalPassword = passwordRepository.findById(passId);
        Password delPassword;
        if(optionalPassword.isPresent()){
            delPassword = optionalPassword.get();
        }else{
            return ResponseEntity.status(404).body(0);
        }

        passwordRepository.delete(delPassword);
        return ResponseEntity.status(200).body(1);
    }

    @GetMapping("/passwordFilterByTitle/{userId}")
    public List<Password> getPasswordsByTitle(@RequestBody Password pass, @PathVariable("userId") Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if(optionalUser.isPresent()){
            user = optionalUser.get();
            return passwordRepository.findAllByTitleAndUser(pass.getTitle(),user);
        }else{
            return null;
        }
    }

    @GetMapping("/passwordFilterByType/{userId}")
    public List<Password> getPasswordsByType(@RequestBody Password pass, @PathVariable("userId") Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if(optionalUser.isPresent()){
            user = optionalUser.get();
            return passwordRepository.findAllByTypeAndUser(pass.getType(),user);
        }else{
            return null;
        }
    }
}
