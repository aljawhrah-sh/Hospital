package model;


public class Administrator extends User {
   public String department;

   public Administrator(){}

    public Administrator(int id, String name, String email, String password, String department) {
        super(id, name, email, password, "ADMIN");
        this.department = department;
    }


    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    //add doctor

    //remove doctor

    //add patient

    //remove patient

    //create report
}
