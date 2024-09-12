package org.example.test;

import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.impl.AuthServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class ModeratorTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UtilisateurRepository utilisateurRepository = null;
        AuthService authService = null;

        try {
            utilisateurRepository = new UtilisateurRepositoryImpl();
            authService = new AuthServiceImpl(utilisateurRepository);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;  // Quitte si la connexion échoue
        }

        while (authService.isUserLoggedIn()) {
            System.out.println("Hello Moderator");
            System.out.println("Menu");
            System.out.println("1. Gestion des espaces");
            System.out.println("2. Gestion des membres");
            System.out.println("3. Gestion des Réservations");
            System.out.println("4. Gestion des Abonnements et Facturations");
            System.out.println("5. Gestion des Services Supplémentaires");
            System.out.println("6. Se déconnecter");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    EspaceTest.main(null);
                    break;
                case 2:
                    // Gestion des membres
                    break;
                case 3:
                    // Gestion des Réservations
                    break;
                case 4:
                    // Gestion des Abonnements
                    break;
                case 5:
                    logout(authService);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void logout(AuthService authService) {
        authService.logout();
        AuthTest.main(null);  // Redirection vers l'authentification
    }
}
