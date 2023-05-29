package est.dsic.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import est.dsic.jee.models.Task;
import jakarta.annotation.Resource;

public class TaskDao {

  
    DataSource myDB;
    private Connection cnx;
    private ArrayList<Task> tasks;
    private Task task;

    public TaskDao() {
        try {
            myDB = InitialContext.doLookup("jdbc/tasks");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void add_Task(Task task) {
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "INSERT INTO tasks (name_task	,date_start	,date_end	,status	,description,id_employee,id_project	) VALUES (?, ?, ?, ?, ?,?,?)");
            ps.setString(1, task.getName_task());
            ps.setString(2, task.getDate_start());
            System.out.println(task.getName_task());
            ps.setString(3, task.getDate_end());
            ps.setString(4, task.getStatus());
            ps.setString(5, task.getDescription());
            ps.setInt(6, task.getId_employee());
            ps.setInt(7, task.getId_project());

            int res = ps.executeUpdate();
            if (res > 0) {
                System.out.println("nadi");
            } else {
                System.out.println("9awad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getAlltasksbyuser(String email){
        tasks = new ArrayList<Task>();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "SELECT id_task, name_task, t.date_start, t.date_end, t.status, t.description, name_project "
                            + "FROM tasks t "
                            + "JOIN employees e ON t.id_employee = e.id_employee "
                            + "JOIN projects p ON t.id_project = p.id_project "
                            + "WHERE e.email = ?");
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                task = new Task();
                task.setId_task(res.getInt("id_task"));
                task.setName_task(res.getString("name_task"));
                task.setDate_start(res.getString("date_start"));
                task.setDate_end(res.getString("date_end"));
                task.setStatus(res.getString("status"));
                task.setDescription(res.getString("description"));
                task.setSelectedProject(res.getString("name_project"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }
    public ArrayList<Task> getAlltasks(){


        tasks = new ArrayList<Task>();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "SELECT id_task, name_task, t.date_start, t.date_end, t.status, t.description, p.name_project , e.name "
                            + "FROM tasks t "
                            + "JOIN employees e ON t.id_employee = e.id_employee "
                            + "JOIN projects p ON t.id_project = p.id_project"
                            
                            );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                task = new Task();
                task.setId_task(res.getInt("id_task"));
                task.setName_task(res.getString("name_task"));
                task.setDate_start(res.getString("date_start"));
                task.setDate_end(res.getString("date_end"));
                task.setStatus(res.getString("status"));
                task.setDescription(res.getString("description"));
                task.setSelectedProject(res.getString("name_project"));
                task.setSelectedemployee(res.getString("name"));
                
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }
    public ArrayList<Task> getAlltasksbyproject(int id){
        tasks = new ArrayList<Task>();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "SELECT id_task, name_task, t.date_start, t.date_end, t.status, t.description, p.name_project , e.name "
                            + "FROM tasks t "
                            + "JOIN employees e ON t.id_employee = e.id_employee "
                            + "JOIN projects p ON t.id_project = p.id_project where t.id_project=?");
                            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                task = new Task();
                task.setId_task(res.getInt("id_task"));
                task.setName_task(res.getString("name_task"));
                task.setDate_start(res.getString("date_start"));
                task.setDate_end(res.getString("date_end"));
                task.setStatus(res.getString("status"));
                task.setDescription(res.getString("description"));
                task.setSelectedProject(res.getString("name_project"));
                task.setSelectedemployee(res.getString("name"));
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }
 public void deletetasksByid_Project(int id){
    try{
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM tasks WHERE id_project = ?");
        ps.setInt(1, id);
        int res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("msa7tih");
        } else {
            System.out.println("9awad");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
 }

 public void openTask(int id_task){
    try{
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement("UPDATE tasks set status = ?  WHERE id_task = ? ");
        ps.setString(1, "Opened");
        ps.setInt(2, id_task);
        int res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("Opened");
        } else {
            System.out.println("9awad");
        }
    }catch(Exception e){
        e.printStackTrace();
    }

 }
 public void closeTask(int id_task){
    try{
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement("UPDATE tasks set status = ?  WHERE id_task = ? ");
        ps.setString(1, "Finished");
        ps.setInt(2, id_task);
        int res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("opened");
        } else {
            System.out.println("9awad");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
 }
public void deleteTask(int id){
    try{
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM tasks WHERE id_task = ? ");
        ps.setInt(1, id);
        int res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("msa7 task");
        } else {
            System.out.println("9awad");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
