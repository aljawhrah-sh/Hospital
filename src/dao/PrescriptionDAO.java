package dao;

import model.Prescription;
import model.Invoice;
import util.AESUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public void addPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescriptions (patient_id, doctor_id, medication, dosage, date_prescribed) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prescription.getPatientId());
            stmt.setInt(2, prescription.getDoctorId());
            stmt.setString(3, AESUtil.encrypt(prescription.getMedication()));
            stmt.setString(4, AESUtil.encrypt(prescription.getDosage()));
            stmt.setString(5, prescription.getDatePrescribed());
            stmt.executeUpdate();
            System.out.println("✅ Prescription added successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding prescription: " + e.getMessage());
        }
    }

    public Prescription getPrescriptionById(int id) {
        Prescription prescription = null;
        String sql = "SELECT * FROM prescriptions WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                prescription = new Prescription(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        AESUtil.decrypt(rs.getString("medication")),
                        AESUtil.decrypt(rs.getString("dosage")),
                        rs.getString("date_prescribed")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving prescription: " + e.getMessage());
        }

        return prescription;
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prescription prescription = new Prescription(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        AESUtil.decrypt(rs.getString("medication")),
                        AESUtil.decrypt(rs.getString("dosage")),
                        rs.getString("date_prescribed")
                );
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving prescriptions: " + e.getMessage());
        }

        return prescriptions;
    }

    public List<Prescription> getPrescriptionsByPatientId(int patientId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prescription p = new Prescription(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        AESUtil.decrypt(rs.getString("medication")),
                        AESUtil.decrypt(rs.getString("dosage")),
                        rs.getString("date_prescribed")
                );
                prescriptions.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving prescriptions: " + e.getMessage());
        }

        return prescriptions;
    }

    public void deletePrescription(int id) {
        String sql = "DELETE FROM prescriptions WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Prescription deleted.");

        } catch (SQLException e) {
            System.out.println("❌ Error deleting prescription: " + e.getMessage());
        }
    }
}
