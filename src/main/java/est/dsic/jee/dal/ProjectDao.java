package est.dsic.jee.dal;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.ResultSet;
import est.dsic.jee.models.Project;

import jakarta.annotation.Resource;

public class ProjectDao {

  
    DataSource myDB;
    private Connection cnx;
    ArrayList<Project> projects;
    ArrayList<String> projects_names;
    String projects_id_names;
    Project pro;
    TaskDao taskdao=new TaskDao();
    public ProjectDao() {
        try {
            myDB = InitialContext.doLookup("jdbc/tasks");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void add_Project(Project project) {
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "INSERT INTO projects (name_project, date_start,date_end,description,status) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, project.getName());
            ps.setString(2, project.getDate_start());
            // System.out.println(project.getDate_start());
            System.out.println(project.getStatus());
            System.out.println(project.getDate_end());
            System.out.println(project.getStatus());
            ps.setString(3, project.getDate_end());
            ps.setString(4, project.getDescription());
            ps.setString(5, project.getStatus());
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

    public ArrayList<Project> getAllProjects() {
        projects = new ArrayList<Project>();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM projects ORDER BY id_project DESC");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                pro = new Project();
                pro.setId_project(res.getInt("id_project"));
                pro.setName(res.getString("name_project"));
                pro.setDate_start(res.getString("date_start"));
                pro.setDate_end(res.getString("date_end"));
                pro.setDescription(res.getString("description"));
                pro.setStatus(res.getString("status"));
                projects.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void deleteProject(int id) {
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM projects WHERE id_project = ?");
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            taskdao.deletetasksByid_Project(id);
            if (res > 0) {
                System.out.println("msa7tih");
            } else {
                System.out.println("9awad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getAllProjects_Names(){

        projects_names = new ArrayList<String>();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("SELECT id_project, name_project FROM projects ORDER BY id_project DESC");
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                projects_id_names = res.getInt("id_project") + ":" + res.getString("name_project");

                projects_names.add(projects_id_names);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects_names;
    }
public Project getProjectbyTask(int id){
    try {
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement(
                "SELECT   p.id_project , p.status , p.name_project  "
                        + "FROM tasks t "
                        + "JOIN projects p ON t.id_project = p.id_project where t.id_task=?");
                        ps.setInt(1, id);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            pro = new Project();
            pro.setId_project(res.getInt("id_project"));
            pro.setName(res.getString("name_project"));
            pro.setStatus(res.getString("status"));
        }
    } catch (Exception e){
        e.printStackTrace();
    }
    return pro;
}
public void modifystatu_project(int id,String statu){
    try{
        cnx = myDB.getConnection();
        PreparedStatement ps = cnx.prepareStatement("UPDATE projects set status = ?  WHERE id_project = ? ");
        ps.setString(1, statu);
        ps.setInt(2, id);
        int res = ps.executeUpdate();
        if (res > 0) {
            System.out.println("update project");
        } else {
            System.out.println("9awad");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
