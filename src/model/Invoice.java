package model;

public class Invoice {
    private int id;
    private int patientId;
    private double amount;
    private String issuedDate;
    private String status;

    public Invoice() {}

    public Invoice(int id, int patientId, double amount, String issuedDate, String status) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.status = status;
    }
    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public double getAmount() { return amount; }
    public String getIssuedDate() { return issuedDate; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setIssuedDate(String issuedDate) { this.issuedDate = issuedDate; }
    public void setStatus(String status) { this.status = status; }


    //create invoice

    //process

    //get all patient invoices
}
