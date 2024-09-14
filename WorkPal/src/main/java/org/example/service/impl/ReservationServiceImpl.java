package org.example.service.impl;

import org.example.entity.Reservation;
import org.example.repository.ReservationRepository;
import org.example.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void addReservation(Reservation reservation)
    {
        reservationRepository.save(reservation);
    }
}
