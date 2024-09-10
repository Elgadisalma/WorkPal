package org.example.test;

import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.UtilisateurService;
import org.example.service.impl.UtilisateurServiceImpl;

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

        UtilisateurService utilisateurService = new UtilisateurServiceImpl(utilisateurRepository);

        while (true) {
            System.out.println("Gestion des utilisateurs :");
            System.out.println("1. S'enregistrer :");
            System.out.println("2. Se connecter :");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    register(utilisateurService, scanner);
                    break;
                case 2:
                    login(utilisateurService, scanner);
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
    private static void register(UtilisateurService utilisateurService, Scanner scanner)
    {
        System.out.println("S'enregistrer :");
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Adresse: ");
        String address = scanner.nextLine();
        System.out.print("phone Number: ");
        String phoneNumber = scanner.nextLine();

        Role role = Role.member;

        Utilisateur newUser = new Utilisateur(fullName, email, password, address, phoneNumber, role);
        utilisateurService.saveUser(newUser);
        System.out.println("Enregistre avec succes.");
    }

    private static void login(UtilisateurService utilisateurService, Scanner scanner)
    {
        System.out.println("Se connecter :");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();


    }
}
