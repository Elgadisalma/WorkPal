package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.entity.Role;
import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private final String tableName = "utilisateur";

    public UtilisateurRepositoryImpl() throws SQLException {}

    @Override
    public List<Utilisateur> findAll()
    {
        final String query = "SELECT * FROM " + tableName;
        final List<Utilisateur> utilisateurs = new ArrayList<>();
        try (final Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Utilisateur utilisateur = mapResultSetToUtilisateur(rs);
                utilisateurs.add(utilisateur);
            }
            return utilisateurs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Utilisateur utilisateur) {
        final String query = "INSERT INTO " + tableName + " (full_name, email, password, address, phone_number, role) VALUES (?,?,?,?,?,?::user_role)";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, utilisateur.getFull_name());
            stmt.setString(count++, utilisateur.getEmail());
            stmt.setString(count++, utilisateur.getPassword());
            stmt.setString(count++, utilisateur.getAddress());
            stmt.setString(count++, utilisateur.getPhoneNumber());
            stmt.setObject(count++, utilisateur.getRole().toString());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Utilisateur utilisateur) {
        final String query = "UPDATE " + tableName + " SET full_name = ?, email = ?, password = ?, phone_number = ?, address = ?, role = ?::user_role WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, utilisateur.getFull_name());
            stmt.setString(count++, utilisateur.getEmail());
            stmt.setString(count++, utilisateur.getPassword());
            stmt.setString(count++, utilisateur.getPhoneNumber());
            stmt.setString(count++, utilisateur.getAddress());
            stmt.setObject(count++, utilisateur.getRole().toString());
            stmt.setLong(count++, utilisateur.getId());

            int executed = stmt.executeUpdate();
            if (executed == 0) {
                throw new SQLException("Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Utilisateur utilisateur = mapResultSetToUtilisateur(rs);
                return Optional.of(utilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
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
    public Optional<Utilisateur> login(String email, String password) {
        final String query = "SELECT * FROM " + tableName + " WHERE email = ? AND password = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getLong("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        Role.valueOf(rs.getString("role"))
                );
                return Optional.of(utilisateur);

            } else {
                System.out.println("Email ou mot de passe incorrect.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Utilisateur> findByName(String fullName) {
        final String query = "SELECT * FROM " + tableName + " WHERE full_name = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fullName);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Utilisateur utilisateur = mapResultSetToUtilisateur(rs);
                return Optional.of(utilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        return new Utilisateur(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone_number"),
                rs.getString("address"),
                Role.valueOf(rs.getString("role"))
        );
    }
}
