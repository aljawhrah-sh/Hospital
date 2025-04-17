package dao;

import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.executeUpdate();
            System.out.println("✅ Appointment added successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error adding appointment: " + e.getMessage());
        }
    }

    public Appointment getAppointmentById(int id) {
        Appointment appointment = null;
        String sql = "SELECT * FROM appointments WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving appointment: " + e.getMessage());
        }

        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date"),
                        rs.getString("status")
                );
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving appointments: " + e.getMessage());
        }

        return appointments;
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Appointment deleted.");

        } catch (SQLException e) {
            System.out.println("❌ Error deleting appointment: " + e.getMessage());
        }
    }
}
