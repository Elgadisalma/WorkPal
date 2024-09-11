package org.example.test;

import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.impl.AuthServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MemberTest {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        UtilisateurRepository utilisateurRepository = new UtilisateurRepositoryImpl();
        AuthService authService = new AuthServiceImpl(utilisateurRepository);


        while (true)
        {
            System.out.println("Hello Member");
            System.out.println("Menu");
            System.out.println("1. Gestion des reservations");
            System.out.println("2. Gestion de mon profil");
            System.out.println("3. Gestion des Abonnements");
            System.out.println("4. Accès aux Services Supplémentaires");
            System.out.println("5. Se deconnecter");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
