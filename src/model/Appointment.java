package model;

public class Appointment {
    public int id;
    public int patientId;
    public int doctorId;
    public String appointmentDate;
    public String status;

    public Appointment() {}

    public Appointment(int id, int patientId, int doctorId, String appointmentDate, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getStatus() {
        return status;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //book appointment

    //view

    //cancel
}
