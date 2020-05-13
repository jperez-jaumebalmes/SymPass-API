package net.jaumebalmes.dam.m13.dao;

import net.jaumebalmes.dam.m13.entities.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Long> {
}
