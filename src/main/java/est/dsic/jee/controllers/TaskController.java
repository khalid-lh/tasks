package est.dsic.jee.controllers;

import java.util.ArrayList;


import est.dsic.jee.models.LoginUser;
import est.dsic.jee.models.Task;
import est.dsic.jee.services.TaskService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@Model
public class TaskController {

    @Inject
    ExternalContext extCtx;
    @Inject
    FacesContext facesCtx;
    @Inject
    @ManagedProperty(value = "#{task}")
    private Task task;
    @Inject
    @ManagedProperty(value = "#{loginUser}")
    private LoginUser loginUser;
    private TaskService taskService = new TaskService();
    ArrayList<Task> tasks;
    private int project_id = 0; 

    public String add_Task(){
        this.task.setStatus("New");
        int selectedEmployeeId = Integer.parseInt(task.getSelectedemployee().split(":")[0]);
        int selectedProjectId = Integer.parseInt(task.getSelectedProject().split(":")[0]);
        this.task.setId_employee(selectedEmployeeId);
        this.task.setId_project(selectedProjectId);
        
        taskService.add_task(this.task);
         reset(this.task);
         EmployeeController.setActiveTabIndex(1);
        return "Manager/manager_interface";
    }

    public ArrayList<Task> getAlltasksbyuser(){
        return taskService.getAlltasksbyuser(loginUser.getEmail_user());
    }
    public ArrayList<Task> getAlltasks(int id){
        System.out.println(id);
        if(id==0){
            return taskService.getAlltasks();
        }else{
            tasks = taskService.getAlltasksbyproject(id);
            setProject_id(0);
            System.out.println("change "+this.project_id);
            return tasks;
        }
    }

    public String  openTask(int id_task){
        taskService.openTask(id_task);
        return "Employee/employee_interface";
    }
    public String  closeTask(int id_task){
        taskService.closeTask(id_task);
        return "Employee/employee_interface";
    }

    public void delete(int id){
        taskService.deleteTask(id);
    }
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id){
        this.project_id = project_id;
    }
    public void setIndex_id_project(int index,int id_project){
        EmployeeController.setActiveTabIndex(index);
        this.setProject_id(id_project);
    }
    //function to reset all properties of Bean task  
    private void reset(Task task) {
        task.setId_project(0);
        task.setId_employee(0);
        task.setName_task(null);
        task.setDate_end(null);
        task.setStatus(null);
        task.setDate_start(null);
        task.setDescription(null);
        task.setId_task(0);
        task.setSelectedProject(null);
        task.setSelectedemployee(null);
    }

    
}
