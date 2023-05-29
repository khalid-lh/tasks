package est.dsic.jee.services;

import java.util.ArrayList;

import est.dsic.jee.dal.ProjectDao;
import est.dsic.jee.dal.TaskDao;
import est.dsic.jee.models.Project;
import est.dsic.jee.models.Task;

public class ProjectService {

    private ProjectDao project_dao = new ProjectDao();
    TaskDao taskDao = new TaskDao();
    ArrayList<Project> projects;
    ArrayList<Task> tasks;
    

    public void add_Project(Project project) {
        project_dao.add_Project(project);
    }

    public ArrayList<Project> getAllProjects(){
        projects = project_dao.getAllProjects();
        
        return projects;
    }

    public void deleteProject(int id){
        project_dao.deleteProject(id);
    }

    public ArrayList<String> getAllProjects_Names() {
        return project_dao.getAllProjects_Names();
    }
}
