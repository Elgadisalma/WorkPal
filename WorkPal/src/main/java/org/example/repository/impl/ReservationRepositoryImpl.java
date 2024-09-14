package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.entity.Reservation;
import org.example.repository.ReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private final String tableName = "reservations";

    public ReservationRepositoryImpl() throws SQLException {}

    @Override
    public void save(Reservation reservation)
    {
        final String query = "INSERT INTO " + tableName + " (date_debut, date_fin, membre_id, espace_id) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, reservation.getDateDebut());
            stmt.setString(count++, reservation.getDateFin());
            stmt.setLong(count++, reservation.getMemberId());
            stmt.setInt(count++, reservation.getEspaceId());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reservation reservation)
    {

    }

    @Override
    public List<Reservation> findAll()
    {
        return List.of();
    }

    @Override
    public List<Reservation> findByDate(String date)
    {
        return List.of();
    }

}
