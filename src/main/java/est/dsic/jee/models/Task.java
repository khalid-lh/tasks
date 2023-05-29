package est.dsic.jee.models;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class Task implements Serializable{
    
    private int id_task;
    private String name_task;
    private String date_start;
    private String date_end;
    private String description;
    private String status;
    private int id_employee;
    private int id_project;
    private String selectedemployee;
    private String selectedProject;


    

    public Task() {
    }

    public int getId_task() {
        return id_task;
    }
    public void setId_task(int id_task) {
        this.id_task = id_task;
    }
    
    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getName_task() {
        return name_task;
    }
    public void setName_task(String name_task) {
        this.name_task = name_task;
    }
    public String getDate_start() {
        return date_start;
    }
    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }
    public String getDate_end() {
        return date_end;
    }
    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelectedemployee() {
        return selectedemployee;
    }

    public void setSelectedemployee(String selectedemployee) {
        this.selectedemployee = selectedemployee;
    }

    public String getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(String selectedProject) {
        this.selectedProject = selectedProject;
    }
    
    
    
}
