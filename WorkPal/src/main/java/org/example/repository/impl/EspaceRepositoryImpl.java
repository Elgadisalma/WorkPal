package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.entity.Espace;
import org.example.entity.EspaceType;
import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.EspaceRepository;

import java.sql.*;
import java.util.ArrayList;
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
        final String query = "UPDATE " + tableName + " SET name = ?, disponibilite = ?, taille = ?, type = ?::espacetype WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, espace.getName());
            stmt.setBoolean(count++, espace.isDisponible());
            stmt.setString(count++, espace.getTaille());
            stmt.setString(count++, espace.getType().toString());
            stmt.setLong(count++, espace.getId());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        final String query = "SELECT * FROM " + tableName;
        final List<Espace> espaces = new ArrayList<>();
        try (final Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Espace espace = mapResultSetToEspace(rs);
                espaces.add(espace);
            }
            return espaces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Espace> findById(Long id)
    {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Espace espace = mapResultSetToEspace(rs);
                return Optional.of(espace);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Espace mapResultSetToEspace(ResultSet rs) throws SQLException
    {
        return new Espace(
                rs.getLong("id"),
                rs.getString("name"),
                EspaceType.valueOf(rs.getString("type")),
                rs.getBoolean("disponibilite"),
                rs.getString("taille")
        );
    }
}
