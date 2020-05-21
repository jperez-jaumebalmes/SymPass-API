package net.jaumebalmes.dam.m13.dao;

import net.jaumebalmes.dam.m13.entities.Password;
import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordRepository extends CrudRepository<Password, Long> {
    public List<Password> findAllByUser(User user);
    public Optional<Password> findById(Long id);
    public List<Password> findAllByTitleAndUser(String title, User user);
    public List<Password> findAllByTypeAndUser(Integer type, User user);
}
