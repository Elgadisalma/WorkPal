package org.example.test;

import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.UtilisateurService;
import org.example.service.impl.UtilisateurServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UtilisateurRepository utilisateurRepository = null;
        try {
            utilisateurRepository = new UtilisateurRepositoryImpl();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        UtilisateurService utilisateurService = new UtilisateurServiceImpl(utilisateurRepository);

        while (true) {
            System.out.println("Gestion des utilisateurs :");
            System.out.println("1. Créer un utilisateur :");
            System.out.println("2. Modifier un utilisateur :");
            System.out.println("3. Afficher les utilisateurs :");
            System.out.println("4. Lister un utilisateur :");
            System.out.println("5. Supprimer un utilisateur :");
            System.out.println("0. Quitter");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    saveUser(utilisateurService, scanner);
                    break;
                case 2:
                    updateUser(utilisateurService, scanner);
                    break;
                case 3:
                    listUsers(utilisateurService);
                    break;
                case 4:
                    findUserById(utilisateurService, scanner);
                    break;
                case 5:
                    deleteUser(utilisateurService, scanner);
                    break;
                case 0:
                    System.out.println("hh");
                    return;
                default:
                    System.out.println("error");
                    break;
            }
        }
    }

    private static void saveUser(UtilisateurService utilisateurService, Scanner scanner) {
        System.out.println("Ajouter un nouvel utilisateur :");
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Adresse: ");
        String address = scanner.nextLine();
        System.out.print("Numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();

        Role role = Role.moderator;

        Utilisateur newUser = new Utilisateur(fullName, email, password, address, phoneNumber, role);
        utilisateurService.saveUser(newUser);
        System.out.println("Utilisateur ajoute avec succes.");
    }

    private static void updateUser(UtilisateurService utilisateurService, Scanner scanner) {
        System.out.print("ID de l'utilisateur à modifier: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nouveau Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Nouvel Email: ");
        String email = scanner.nextLine();
        System.out.print("Nouveau Password: ");
        String password = scanner.nextLine();
        System.out.print("Nouvelle Adresse: ");
        String address = scanner.nextLine();
        System.out.print("Nouveau Numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();

        Role role = Role.moderator;

        Utilisateur utilisateur = new Utilisateur(fullName, email, password, address, phoneNumber, role);
        utilisateurService.updateUser(utilisateur, id);
        System.out.println("Utilisateur mis à jour avec succès.");
    }

    private static void listUsers(UtilisateurService utilisateurService) {
        List<Utilisateur> utilisateurs = utilisateurService.findAllUsers();
        System.out.println("Liste des utilisateurs :");
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
    }

    private static void findUserById(UtilisateurService utilisateurService, Scanner scanner) {
        System.out.print("ID de l'utilisateur à rechercher: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            Utilisateur utilisateur = utilisateurService.findUserById(id);
            System.out.println(utilisateur);
        } catch (RuntimeException e) {
            System.out.println("Not found");
        }
    }

    private static void deleteUser(UtilisateurService utilisateurService, Scanner scanner) {
        System.out.print("ID de l'utilisateur à supprimer: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        utilisateurService.deleteUser(id);
        System.out.println("Deleted successfully");
    }
}
