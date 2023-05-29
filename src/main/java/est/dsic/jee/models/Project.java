package est.dsic.jee.models;
import java.io.Serializable;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;



@Named("project")
@SessionScoped
public class Project implements Serializable {

    private int id_project;
    private String name;
    private String date_start;
    private String date_end;
    private String description;
    private String status;
    private String selectedProject;

    
    public Project() {
    }
    public int getId_project() {
        return id_project;
    }
    public void setId_project(int id_project) {
        this.id_project = id_project;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getSelectedProject() {
        return selectedProject;
    }
    public void setSelectedProject(String selctedProject) {
        this.selectedProject = selctedProject;
    }
     
    
    
}
