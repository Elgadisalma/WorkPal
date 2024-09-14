package org.example.repository;

import org.example.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    void save(Reservation reservation);
    void delete(Reservation reservation);
    List<Reservation> findAll();
    List<Reservation> findByDate(String date);

}
