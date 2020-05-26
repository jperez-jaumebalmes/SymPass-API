package net.jaumebalmes.dam.m13.controllers;

import net.jaumebalmes.dam.m13.dao.UserRepository;
import net.jaumebalmes.dam.m13.entities.LoginForm;
import net.jaumebalmes.dam.m13.entities.Password;
import net.jaumebalmes.dam.m13.entities.User;
import net.jaumebalmes.dam.m13.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/user")
    public ResponseEntity<String> newUser(@RequestBody User newUser) {
        Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());
        if(optionalUser.isPresent()){
            return ResponseEntity.status(409).body("409");
        }
        String salt = PasswordUtils.getSalt(30);
        String securePassword = PasswordUtils.generateSecurePassword(newUser.getPassword(),salt);
        newUser.setPassword(securePassword);
        newUser.setSalt(salt);
        userRepository.save(newUser);
        return ResponseEntity.status(200).body("User creat amb id "+newUser.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> logInUser(@RequestBody LoginForm loginForm){
        HttpHeaders responseHeaders = new HttpHeaders();
        //Optional<User> optUser = userRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        Optional<User> optUser = userRepository.findByEmail(loginForm.getEmail());
        if(optUser.isPresent()){
            User user = optUser.get();
            boolean passwordMatch = PasswordUtils.verifyUserPassword(loginForm.getPassword(),user.getPassword(),user.getSalt());
            if(passwordMatch){
                long id = user.getId();
                int status = 200;
                String statusTotal = ""+status+id;
                responseHeaders.set("Allow-SymPass-Access","2");
                return ResponseEntity.status(Integer.parseInt(statusTotal)).body(2);
            }else{
                responseHeaders.set("Allow-SymPass-Access","1");
                return ResponseEntity.status(406).body(1);
            }
        }else{
            responseHeaders.set("Allow-SymPass-Access","0");
            return ResponseEntity.status(401).body(0);
        }
<<<<<<< HEAD

    }

    @PostMapping("/recover")
    public ResponseEntity<String> recoverPass(@RequestBody User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        SimpleMailMessage email = new SimpleMailMessage();
        if(optionalUser.isPresent()){
            user = optionalUser.get();
            email.setTo(user.getEmail());
            email.setSubject("Email Recovery from user "+user.getName());
            email.setText("The password of your SymPass's account: "+user.getPassword());

            mailSender.send(email);
            return ResponseEntity.status(200).body("Existe el email");
        }else{
            return ResponseEntity.status(404).body("No existe el email");
        }

    }

    @GetMapping("/user")
    public Iterable<User> getUserList(){
        return userRepository.findAll();
=======
>>>>>>> 61617f1070c7fc650a026131fb1ea75144671993
    }
}
