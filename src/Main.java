import dao.*;
import model.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Test BCrypt password hashing
        String plainPassword = "mySecurePassword123";
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        System.out.println("Hashed: " + hashed);
        System.out.println("Correct password? " + BCrypt.checkpw("mySecurePassword123", hashed));
        System.out.println("Wrong password? " + BCrypt.checkpw("wrongPass", hashed));

        // Prescription test
        int patientId = 1;
        int doctorId = 1;

        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

        // Add test prescriptions
        Prescription p1 = new Prescription(0, patientId, doctorId, "Ibuprofen", "200mg twice daily", "2025-04-01");
        Prescription p2 = new Prescription(0, patientId, doctorId, "Paracetamol", "500mg twice a day", "2025-04-02");
        Prescription p3 = new Prescription(0, patientId, doctorId, "Amoxicillin", "250mg three times a day", "2025-04-03");

        prescriptionDAO.addPrescription(p1);
        prescriptionDAO.addPrescription(p2);
        prescriptionDAO.addPrescription(p3);

        // Fetch and print
        List<Prescription> prescriptions = prescriptionDAO.getPrescriptionsByPatientId(patientId);
        System.out.println("\n✅ Prescriptions:");
        for (Prescription p : prescriptions) {
            System.out.println(p.getId() + " | " + p.getMedication() + " | " + p.getDosage() + " | " + p.getDatePrescribed());
        }
    }
}