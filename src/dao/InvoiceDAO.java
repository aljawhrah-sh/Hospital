package dao;

import model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    public void addInvoice(Invoice invoice) {
    String sql = "INSERT INTO invoices (patient_id, amount, date_issued, status) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseManager.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, invoice.getPatientId());
        stmt.setDouble(2, invoice.getAmount());
        stmt.setString(3, invoice.getIssuedDate());
        stmt.setString(4, invoice.getStatus());
        stmt.executeUpdate();
        System.out.println("✅ Invoice added successfully.");

    } catch (SQLException e) {
        System.out.println("❌ Error adding invoice: " + e.getMessage());
    }
}

    public List<Invoice> getInvoicesByPatientId(int patientId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices WHERE patient_id = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getDouble("amount"),
                        rs.getString("date_issued"),
                        rs.getString("status")
                );
                invoices.add(i);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving invoices: " + e.getMessage());
        }

        return invoices;
    }
}
