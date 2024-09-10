package org.example.service;

import org.example.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> findAllUsers();
    Utilisateur findUserById(Long id);
    void saveUser(Utilisateur utilisateur);
    void updateUser(Utilisateur utilisateur, Long id);
    void deleteUser(Long id );
    Utilisateur findUserByName(String fullName);

}
