package org.example.test;

import org.example.entity.Reservation;
import org.example.entity.Utilisateur;
import org.example.repository.ReservationRepository;
import org.example.repository.UtilisateurRepository;
import org.example.repository.impl.ReservationRepositoryImpl;
import org.example.repository.impl.UtilisateurRepositoryImpl;
import org.example.service.AuthService;
import org.example.service.ReservationService;
import org.example.service.impl.AuthServiceImpl;
import org.example.service.impl.ReservationServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class ReservationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReservationRepository reservationRepository = null;
        UtilisateurRepository utilisateurRepository = null;
        try {
            reservationRepository = new ReservationRepositoryImpl();
            utilisateurRepository = new UtilisateurRepositoryImpl();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        AuthService authService = new AuthServiceImpl(utilisateurRepository);
        ReservationService reservationService = new ReservationServiceImpl(reservationRepository);

        while (authService.isUserLoggedIn()) {
            System.out.println("Gestion des reservations");
            System.out.println("1. Ajouter reservation");
            System.out.println("6. Exit");

            System.out.print("Choisissez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    createReservation(reservationService, scanner, authService);
                    break;
                case 6:

                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void createReservation(ReservationService reservationService, Scanner scanner, AuthService authService)
{
    System.out.println("Ajouter une reservation :");
    System.out.print("dateDebut: ");
    String dateDebut = scanner.nextLine();
    System.out.print("dateFin: ");
    String dateFin = scanner.nextLine();
    System.out.print("ID de l'espace: ");
    int espaceId = scanner.nextInt();

    Utilisateur currentUser = authService.getCurrentUser();
    Long memberId = currentUser.getId();

    Reservation reservation = new Reservation(dateDebut, dateFin, memberId, espaceId);
    reservationService.addReservation(reservation);
    System.out.println("Réservation créée avec succès.");
}
}