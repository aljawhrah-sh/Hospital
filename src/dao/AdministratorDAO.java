package dao;

import model.Administrator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO {

    public void addAdministrator(int userId, Administrator admin) {
        String sql = "INSERT INTO administrators (user_id, department) VALUES (?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, admin.getDepartment());
            stmt.executeUpdate();
            System.out.println("✅ Administrator added successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding administrator: " + e.getMessage());
        }
    }

    public Administrator getAdministratorById(int userId) {
        Administrator admin = null;
        String sql = "SELECT a.*, u.name, u.email, u.password, u.role FROM administrators a " +
                "JOIN users u ON a.user_id = u.id WHERE a.user_id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                admin = new Administrator(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("department")
                );
                admin.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving administrator: " + e.getMessage());
        }

        return admin;
    }

    public List<Administrator> getAllAdministrators() {
        List<Administrator> admins = new ArrayList<>();
        String sql = "SELECT a.*, u.name, u.email, u.password, u.role FROM administrators a " +
                "JOIN users u ON a.user_id = u.id";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Administrator admin = new Administrator(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("department")
                );
                admin.setId(rs.getInt("id"));
                admins.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving administrators: " + e.getMessage());
        }

        return admins;
    }

    public void deleteAdministrator(int id) {
        String sql = "DELETE FROM administrators WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Administrator deleted.");

        } catch (SQLException e) {
            System.out.println("❌ Error deleting administrator: " + e.getMessage());
        }
    }
}
