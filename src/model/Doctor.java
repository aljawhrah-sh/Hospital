package model;


public class Doctor extends User{

    public String specialization;

    public Doctor() {
        this.setRole("DOCTOR");
    }

    public Doctor(int id, String name, String email, String password, String specialization) {
        super(id, name, email, password, "DOCTOR");
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
