package est.dsic.jee.models;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;



@Named("employee")
@SessionScoped
public class Employee implements Serializable{
    private int id_employee;
    private String name;
    private String email;
    private String fonction;
    private String pwd;
    private String selectedemployee;

    public Employee() {
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSelectedemployee() {
        return selectedemployee;
    }

    public void setSelectedemployee(String selectedemployee) {
        this.selectedemployee = selectedemployee;
    }

}
