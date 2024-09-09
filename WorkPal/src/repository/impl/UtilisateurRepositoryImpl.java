package repository.impl;

import config.DatabaseConnection;
import entity.Role;
import entity.Utilisateur;
import repository.UtilisateurRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private final String tableName = "utilisateur";


    @Override
    public List<Utilisateur> findAll()
    {
        final String query = "SELECT * FROM " + tableName;
        final List<Utilisateur> utilisateurs = new ArrayList<>();
        try (final Statement stmt = connection.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                final Utilisateur utilisateur = new Utilisateur(
                        rs.getLong("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        Role.valueOf(rs.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
            return utilisateurs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Utilisateur utilisateur)
    {
        final String query = "INSERT INTO " + tableName + " (full_name, email, password, address, phone_number, role) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            stmt.setString(count++, utilisateur.getFull_name());
            stmt.setString(count++, utilisateur.getEmail());
            stmt.setString(count++, utilisateur.getPassword());
            stmt.setString(count++, utilisateur.getPhoneNumber());
            stmt.setString(count++, utilisateur.getAddress());
            stmt.setObject(count++, utilisateur.getRole().toString());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Error");
            } else {
                System.out.println("saved successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Utilisateur utilisateur)
    {
        final String query = "UPDATE " + tableName + " SET full_name = ?, email = ?, password = ?, phone_number = ?, address = ?, role = ?::role WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)){
            int count = 1;
            stmt.setString(count++, utilisateur.getFull_name());
            stmt.setString(count++, utilisateur.getEmail());
            stmt.setString(count++, utilisateur.getPassword());
            stmt.setString(count++, utilisateur.getPhoneNumber());
            stmt.setString(count++, utilisateur.getAddress());
            stmt.setObject(count++, utilisateur.getRole().toString());
            stmt.setLong(count++, utilisateur.getId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Error");
            } else {
                System.out.println("updated successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Utilisateur> findById(Long id)
    {
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setLong(1, id);

            final ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                final Utilisateur utilisateur = new Utilisateur(
                        rs.getLong("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        Role.valueOf(rs.getString("role"))
                );
                return Optional.of(utilisateur);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id)
    {
        final String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try (final PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setLong(1, id);

            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
