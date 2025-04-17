package model;

public class MedicalRecord {
    private int id;
    private int patientId;
    private int doctorId;
    private String notes;
    private String recordDate;

    public MedicalRecord() {}

    public MedicalRecord(int id, int patientId, int doctorId, String notes, String recordDate) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.notes = notes;
        this.recordDate = recordDate;
    }
    public int getId() {
        return id;
    }
    public int getPatientId() {
        return patientId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public String getNotes() {
        return notes;
    }
    public String getRecordDate() {
        return recordDate;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }


    //get record

    //update

    //get all records
}
