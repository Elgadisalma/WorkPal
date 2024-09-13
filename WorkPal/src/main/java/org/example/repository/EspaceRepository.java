package org.example.repository;

import org.example.entity.Espace;
import org.example.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface EspaceRepository {

    void save(Espace espace);
    void update(Espace espace);
    void deleteById(Long id);
    List<Espace> findAll();
    Optional<Espace> findByName(String name);


}
