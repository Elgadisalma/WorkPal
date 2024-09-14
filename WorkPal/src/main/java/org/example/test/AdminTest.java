package org.example.test;

import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.impl.AuthServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UtilisateurRepository utilisateurRepository = null;
        AuthService authService = null;

        try {
            utilisateurRepository = new UtilisateurRepositoryImpl();
            authService = new AuthServiceImpl(utilisateurRepository);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        while (authService.isUserLoggedIn())
        {
            System.out.println("Hello Admin");
            System.out.println("Menu");
            System.out.println("1. Gestion des utilisateurs");
            System.out.println("2. Se deconnecter");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix)
            {
                case 1:
                    UserTest.main(null);
                    break;
                case 2:
                    logout(authService);
                    break;
                default:
                    System.out.println("Choix invalide");

            }
        }
    }

    private static void logout(AuthService authService)
    {
        authService.logout();
        AuthTest.main(null);
    }
}
