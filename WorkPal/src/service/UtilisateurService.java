package service;

import entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> findAllUsers();
    Utilisateur findUserById(Long id);
    void saveUser(Utilisateur utilisateur);
    void updateUser(Utilisateur utilisateur);
    void deleteUser(Utilisateur utilisateur);
}
