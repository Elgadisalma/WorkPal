package org.example.service;

import org.example.entity.Utilisateur;

public interface AuthService {
    void register(Utilisateur utilisateur);
    void login(String email, String password);
    void logout();
    boolean isUserLoggedIn();
    boolean isModerator();
    Utilisateur getCurrentUser();
    void forgotPassword(String email);
}
