package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.entity.Espace;
import org.example.repository.EspaceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EspaceRepositoryImpl implements EspaceRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private final String tableName = "espace";

    public EspaceRepositoryImpl() throws SQLException {}


    @Override
    public void save(Espace espace)
    {
        final String query = "INSERT INTO " + tableName + " (name, disponibilite, taille, type) VALUES (?,?,?,?::espacetype)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, espace.getName());
            stmt.setBoolean(count++, espace.isDisponible());
            stmt.setString(count++, espace.getTaille());
            stmt.setString(count++, espace.getType().toString());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Espace espace)
    {

    }

    @Override
    public void deleteById(Long id)
    {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Espace> findAll()
    {
        return List.of();
    }

    @Override
    public Optional<Espace> findByName(String name)
    {
        return Optional.empty();
    }
}
