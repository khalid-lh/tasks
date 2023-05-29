package est.dsic.jee.controllers;

import java.text.ParseException;

import java.util.ArrayList;

import est.dsic.jee.models.Project;
import est.dsic.jee.services.ProjectService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.ManagedProperty;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@Model
public class ProjectController {

    @Inject
    FacesContext facesCtx;
    @Inject
    @ManagedProperty(value = "#{project}")
    private Project project;
    private ProjectService project_Service = new ProjectService();
    ArrayList<Project> projects = new ArrayList<Project>();

    public String add_Project() throws ParseException{
        this.project.setStatus("New");
        project_Service.add_Project(this.project);
        this.project.setDate_end(null);
        project.setId_project(0);
        project.setName(null);
        project.setDate_start(null);
        project.setDate_end(null);
        project.setDescription(null);
        project.setStatus(null);
        EmployeeController.setActiveTabIndex(0);
        return "Manager/manager_interface";
    }

    public ArrayList<Project> getAllProject(){
        return project_Service.getAllProjects();
    }

    public String delete_Project(Project project){
        
        project_Service.deleteProject(project.getId_project());
        EmployeeController.setActiveTabIndex(0);
        return "Manager/manager_interface";
    }

    public ArrayList<String> getAllProjects_Names(){
        return project_Service.getAllProjects_Names();
    }

    
    
}
