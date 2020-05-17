package net.jaumebalmes.dam.m13.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class LoginForm {
    String email;
    String password;

    public LoginForm() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
