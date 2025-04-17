package model;


public class  Patient extends User{

    public String medicalHistory;

    public Patient(){

    }

    public Patient(int id, String name, String email, String password, String medicalHistory) {
        super(id, name, email, password, "PATIENT");
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                '}';
    }
}
