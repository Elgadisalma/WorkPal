package test;

import repository.UtilisateurRepository;
import repository.impl.UtilisateurRepositoryImpl;
import service.UtilisateurService;
import service.impl.UtilisateurServiceImpl;

import java.util.Scanner;

public class UtilisateurTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UtilisateurRepository repository = new UtilisateurRepositoryImpl();
        UtilisateurService utilisateurService = new UtilisateurServiceImpl(repository);


        while (true) {
            System.out.println("\n Gestion des utilisateur : ");
            System.out.println("\n 1.Creer un utilisateur : ");
            System.out.println("\n 2.Modifier un utilisateur : ");
            System.out.println("\n 3.Afficher les utilisateurs : ");
            System.out.println("\n 4.Lister un utilisateur : ");
            System.out.println("\n 5.Supprimer un utilisateur : ");
            System.out.println("\n 0. Quitter\n");

            System.out.println("Choisissez votre choix : ");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix){
                case 1:
                    createUser(scanner, utilisateurService);
                    break;
                default:
                    System.out.println("Votre choix introuvable");
            }

        }
        private void createUser(){
            
        }
    }
}
