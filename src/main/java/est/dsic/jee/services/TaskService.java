package est.dsic.jee.services;
import java.util.ArrayList;
import est.dsic.jee.dal.ProjectDao;
import est.dsic.jee.dal.TaskDao;
import est.dsic.jee.models.Project;
import est.dsic.jee.models.Task;

public class TaskService {

    TaskDao taskDao = new TaskDao();
    private ProjectDao project_dao = new ProjectDao();
    ArrayList<Project> projects;
    ArrayList<Task> tasks;
    private Project pro;
    
    int statu_new, status_open, statu_fin = 0;
    public TaskService() {
    }

    public void add_task(Task task) {
        taskDao.add_Task(task);
    }

    public ArrayList<Task> getAlltasksbyuser(String email) {
        return taskDao.getAlltasksbyuser(email);
    }

    public ArrayList<Task> getAlltasks() {
        return taskDao.getAlltasks();
    }

    public ArrayList<Task> getAlltasksbyproject(int id) {
        return taskDao.getAlltasksbyproject(id);
    }

    public void openTask(int id_task){
        taskDao.openTask(id_task);
        pro = project_dao.getProjectbyTask(id_task);
        tasks = taskDao.getAlltasksbyproject(pro.getId_project());
        if(!(tasks.isEmpty())){
            for(Task t : tasks){
                if(t.getStatus().equals("New")){
                    statu_new++;
                }else if(t.getStatus().equals("Opened")){
                    status_open++;
                }else if(t.getStatus().equals("Finished")){
                    statu_fin++;
                }
            }
            System.out.println("statu_new :"+statu_new+" / statu_open :"+status_open+" / statu_fin "+statu_fin);
            if(statu_new==0 && statu_fin==0 && status_open==0){
                pro.setStatus("New");
            }
            else if(statu_new > status_open && statu_new > statu_fin){
                pro.setStatus("New");
            }else if(status_open>statu_fin && status_open>statu_new){
                pro.setStatus("Opened");

            }else if(statu_fin>statu_new && statu_fin>status_open){
                pro.setStatus("Finished");
            }else{
                pro.setStatus("Opened");
            }
            statu_fin=0;
            statu_new=0;
            status_open=0;
        }else{
            pro.setStatus("New");
        }
        project_dao.modifystatu_project(pro.getId_project(),pro.getStatus());  

    }

    public void closeTask(int id_task) {
        taskDao.closeTask(id_task);
        pro= project_dao.getProjectbyTask(id_task);
        tasks = taskDao.getAlltasksbyproject(pro.getId_project());
        if(!(tasks.isEmpty())){
            for(Task t : tasks){
                if(t.getStatus().equals("New")){
                    statu_new++;
                }else if(t.getStatus().equals("Opened")){
                    status_open++;
                }else if(t.getStatus().equals("Finished")){
                    statu_fin++;
                }
            }
            System.out.println("statu_new :"+statu_new+" / statu_open :"+status_open+" / statu_fin "+statu_fin);
            if(statu_new==0 && statu_fin==0 && status_open==0){
                pro.setStatus("New");
            }
            else if(statu_new > status_open && statu_new > statu_fin){
                pro.setStatus("New");
            }else if(status_open>statu_fin && status_open>statu_new){
                pro.setStatus("Opened");
  
            }else if(statu_fin>statu_new && statu_fin>status_open){
                pro.setStatus("Finished");
            }else{
                pro.setStatus("Opened");
            }
            statu_fin=0;
            statu_new=0;
            status_open=0;
        }else{
            pro.setStatus("New");
        }
        project_dao.modifystatu_project(pro.getId_project(),pro.getStatus());  
    }
    public void deleteTask(int id){
        taskDao.deleteTask(id);

    }
}
