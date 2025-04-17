package model;

public class Prescription {
    private int id;
    private int patientId;
    private int doctorId;
    private String medication;
    private String dosage;
    private String datePrescribed;

    public Prescription() {}

    public Prescription(int id, int patientId, int doctorId, String medication, String dosage, String datePrescribed) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medication = medication;
        this.dosage = dosage;
        this.datePrescribed = datePrescribed;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getMedication() {
        return medication;
    }
    public void setMedication(String medication) {
        this.medication = medication;
    }
    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    public String getDatePrescribed() {
        return datePrescribed;
    }
    public void setDatePrescribed(String datePrescribed) {
        this.datePrescribed = datePrescribed;
    }


}
