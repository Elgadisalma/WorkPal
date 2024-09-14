package org.example.test;

import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.impl.AuthServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UtilisateurRepository utilisateurRepository = null;
        try {
            utilisateurRepository = new UtilisateurRepositoryImpl();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        AuthService authService = new AuthServiceImpl(utilisateurRepository);

        while (true) {
            System.out.println("Gestion des utilisateurs :");
            System.out.println("1. S'enregistrer :");
            System.out.println("2. Se connecter :");
            System.out.println("3. Oublier le mot de passe :");
            System.out.println("0. Quitter");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    register(authService, scanner);
                    break;
                case 2:
                    login(authService, scanner);
                    break;
                case 3:
//                    forgetPassword(authService, scanner);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private static void register(AuthService authService, Scanner scanner) {
        System.out.println("S'enregistrer :");
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Adresse: ");
        String address = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Role role = Role.member;

        Utilisateur newUser = new Utilisateur(fullName, email, password, address, phoneNumber, role);
        authService.register(newUser);
        System.out.println("Enregistrement réussi.");
    }

    private static void login(AuthService authService, Scanner scanner) {
        System.out.println("Se connecter :");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        authService.login(email, password);

        if (authService.isUserLoggedIn()) {
            Utilisateur currentUser = authService.getCurrentUser();
            System.out.println("Bienvenue, " + currentUser.getFull_name());

            if (currentUser.getRole() == Role.moderator) {
                ModeratorTest.main(null);
            } else if (currentUser.getRole() == Role.admin) {
                AdminTest.main(null);
            } else {
                MemberTest.main(null);
            }
        } else {
            System.out.println("Échec de la connexion. Veuillez vérifier vos identifiants.");
        }
    }

//    private static void forgetPassword(AuthService authService, Scanner scanner) {
//        System.out.println("Mot de passe oublié :");
//        System.out.print("Email: ");
//        String email = scanner.nextLine();
//
//        authService.forgetPassword(email);
//
//        System.out.println("Si l'email existe, un code de réinitialisation vous a été envoyé.");
//    }
}
