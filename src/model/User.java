package model;


public  class User {
 public int id;
 public String name;
 public String email;
 public String password;
 public String role;

    public User() {
        // required by JDBC + JavaFX
    }

 public User(int id, String name, String email, String password, String role) {
     this.id = id;
     this.name = name;
     this.email = email;
     this.password = password;
     this.role = role;
 }
 public int getId() {
     return id;
 }
 public String getName() {
     return name;
 }
public String getEmail() {
     return email;
}
public String getPassword() {
     return password;
}
public String getRole() {
     return role;
}
public void setRole(String role) {
     this.role = role;
}
public void setPassword(String password) {
     this.password = password;
}
public void setEmail(String email) {
     this.email = email;
}
public void setName(String name) {
     this.name = name;
}
public void setId(int id) {
     this.id = id;
}

@Override
    public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            '}';
}
    }