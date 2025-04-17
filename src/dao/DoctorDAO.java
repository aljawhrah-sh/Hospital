package dao;

import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(int userId, Doctor doctor) {
        String sql = "INSERT INTO doctors (user_id, specialization) VALUES (?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, doctor.getSpecialization());
            stmt.executeUpdate();
            System.out.println("✅ Doctor added successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding doctor: " + e.getMessage());
        }
    }

    public Doctor getDoctorById(int userId) {
        Doctor doctor = null;
        String sql = "SELECT d.*, u.name, u.email, u.password, u.role FROM doctors d " +
                "JOIN users u ON d.user_id = u.id WHERE d.user_id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("specialization")
                );
                doctor.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving doctor: " + e.getMessage());
        }

        return doctor;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT d.*, u.name, u.email, u.password, u.role FROM doctors d " +
                "JOIN users u ON d.user_id = u.id";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("specialization")
                );
                doctor.setId(rs.getInt("id"));
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving doctors: " + e.getMessage());
        }

        return doctors;
    }

    public void deleteDoctor(int id) {
        String sql = "DELETE FROM doctors WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Doctor deleted.");

        } catch (SQLException e) {
            System.out.println("❌ Error deleting doctor: " + e.getMessage());
        }
    }
}
