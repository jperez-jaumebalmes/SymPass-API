package net.jaumebalmes.dam.m13.dao;

import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByEmailAndPassword(String email, String Password);
    public Optional<User> findByEmail(String email);

}
