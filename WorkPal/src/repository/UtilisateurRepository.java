package repository;

import entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository {

    void save(Utilisateur utilisateur);
    void update(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    void deleteById(Long id);
    List<Utilisateur> findAll();

}
