package dao;

import model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class UserDAO {
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            System.out.println("✅ User added with hashed password.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding user: " + e.getMessage());
        }
    }

    public User getByEmailAndPassword(String email, String inputPassword) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(inputPassword, storedHash)) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            storedHash,
                            rs.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error authenticating user: " + e.getMessage());
        }

        return user;
    }
}

