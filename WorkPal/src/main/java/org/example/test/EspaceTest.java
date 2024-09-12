package org.example.test;

import org.example.entity.Espace;
import org.example.entity.EspaceType;
import org.example.repository.EspaceRepository;
import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.EspaceRepositoryImpl;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.EspaceService;
import org.example.service.impl.AuthServiceImpl;
import org.example.service.impl.EspaceServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class EspaceTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EspaceRepository espaceRepository = null;
        UtilisateurRepository utilisateurRepository = null;
        try {
            espaceRepository = new EspaceRepositoryImpl();
            utilisateurRepository = new UtilisateurRepositoryImpl();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        AuthService authService = new AuthServiceImpl(utilisateurRepository);
        EspaceService espaceService = new EspaceServiceImpl(espaceRepository, authService);

        while (authService.isUserLoggedIn()) {
            System.out.println("Gestion des espaces");
            System.out.println("1. Ajouter espace");
            System.out.println("2. Modifier un espace");
            System.out.println("3. Supprimer un espace");
            System.out.println("4. Afficher tous les espaces");
            System.out.println("5. Afficher un espace");
            System.out.println("6. Exit");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    createEspace(espaceService, scanner);
                    break;
                case 2:
                    updateEspace(espaceService, scanner);
                    break;
                case 3:
                    deleteEspace(espaceService, scanner);
                    break;
                case 4:
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    ModeratorTest.main(null);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void createEspace(EspaceService espaceService, Scanner scanner)
    {
        System.out.println("Ajouter un nouvel espace :");
        System.out.print("Nom de l'espace: ");
        String name = scanner.nextLine();
        System.out.print("Taille de l'espace: ");
        String taille = scanner.nextLine();

        System.out.println("Choisissez le type d'espace:");
        System.out.println("1. Salle");
        System.out.println("2. Café");
        System.out.println("3. Jardin");
        int typeChoix = scanner.nextInt();
        scanner.nextLine();

        EspaceType type;
        switch (typeChoix) {
            case 1:
                type = EspaceType.salle;
                break;
            case 2:
                type = EspaceType.cafe;
                break;
            case 3:
                type = EspaceType.jardin;
                break;
            default:
                System.out.println("Erreur");
                return;
        }

        boolean disponibilite = true;

        Espace espace = new Espace(name, type, disponibilite, taille);

        espaceService.createEspace(espace);

    }

    private static void deleteEspace(EspaceService espaceService, Scanner scanner)
    {
        System.out.print("ID de l'espace à supprimer: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        espaceService.deleteEspace(id);
        System.out.println("Deleted successfully");
    }
}
