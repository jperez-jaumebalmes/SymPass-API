package net.jaumebalmes.dam.m13.dao;

import net.jaumebalmes.dam.m13.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
