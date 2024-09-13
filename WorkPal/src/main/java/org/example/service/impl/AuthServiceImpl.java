package org.example.service.impl;

import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.example.service.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository userRepository;
    private static Utilisateur currentUser = null;

    public AuthServiceImpl(UtilisateurRepository repository)
    {
        this.userRepository = repository;
    }

    @Override
    public void register(Utilisateur utilisateur)
    {
        userRepository.save(utilisateur);
    }

    @Override
    public void login(String email, String password)
    {
        Optional<Utilisateur> utilisateur = userRepository.login(email, password);
        if (utilisateur.isPresent()) {
            currentUser = utilisateur.get();
        } else {
            System.out.println("Email ou mot de passe incorrect.");
        }
    }

    @Override
    public void logout()
    {
        currentUser = null;
        System.out.println("Logged ou successfully");
    }

    @Override
    public boolean isUserLoggedIn()
    {
        return currentUser != null;
    }

    @Override
    public Utilisateur getCurrentUser()
    {
        return currentUser;
    }

    @Override
    public Boolean isModerator() {
        return currentUser != null && currentUser.getRole() == Role.moderator;
    }

//
//    @Override
//    public void forgotPassword(String email)
//    {
//
//    }

  public boolean isModerator() {
        return currentUser != null && "moderator".equals(currentUser.getRole());
    }


}
