package org.example.service;

import org.example.entity.Utilisateur;

public interface AuthService {
    void register(Utilisateur utilisateur);
    void login(String email, String password);
    void logout();
    boolean isUserLoggedIn();
    Utilisateur getCurrentUser();
}
