package net.jaumebalmes.dam.m13.dao;

import net.jaumebalmes.dam.m13.entities.Password;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PasswordRepository extends CrudRepository<Password, Long> {
    public List<Password> findAllByUser(User user);
}
