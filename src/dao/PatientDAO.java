package dao;

import model.Patient;
import util.AESUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class PatientDAO {
    public void addPatient(int userId, Patient patient) {
        String sql = "INSERT INTO patients (user_id, medical_history) VALUES (?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, AESUtil.encrypt(patient.getMedicalHistory()));
            stmt.executeUpdate();
            System.out.println("✅ Patient added successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding patient: " + e.getMessage());
        }
    }

    public Patient getPatientById(int userId) {
        Patient patient = null;
        String sql = "SELECT p.*, u.name, u.email, u.password, u.role FROM patients p JOIN users u ON p.user_id = u.id WHERE p.user_id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                patient = new Patient(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        AESUtil.decrypt(rs.getString("medical_history"))
                );
                patient.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving patient: " + e.getMessage());
        }

        return patient;
    }
}