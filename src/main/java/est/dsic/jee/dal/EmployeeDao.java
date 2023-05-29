package est.dsic.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import est.dsic.jee.models.LoginUser;

import est.dsic.jee.models.Employee;
import jakarta.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class EmployeeDao {

    
   
    
    DataSource myDB;
    private Connection cnx;
    Employee employee;
    ArrayList<String> employees_names;
    ArrayList<Employee> employees;
    String employees_id_names;
    


    public EmployeeDao(){ 
        try {
            myDB = InitialContext.doLookup("jdbc/tasks");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }      
    }

    public Employee authentifier(LoginUser login){
        this.employee = new Employee();
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM employees where email=? and pwd=?");
            ps.setString(1, login.getEmail_user());
            ps.setString(2, login.getPwd_user());
            ResultSet res = ps.executeQuery();
            while(res.next()){
                this.employee.setName(res.getString("name"));
                System.out.println(res.getString("name"));
                this.employee.setEmail((res.getString("email")));
                this.employee.setFonction((res.getString("fonction")));
                System.out.println(this.employee.getEmail());
                return this.employee;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void add_Emp(Employee employee){
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("INSERT INTO employees (name, email, fonction ,pwd) VALUES (?, ?, ?, ?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getFonction());
            ps.setString(4, employee.getPwd());
            int res = ps.executeUpdate();
            if(res>0){
                System.out.println("nadi");
            }else{
                System.out.println("9awad");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Employee> getAllEmployees(String manager_email){

        employees = new ArrayList<Employee>();
        try{
            cnx = myDB.getConnection();
           
            PreparedStatement ps = cnx.prepareStatement("SELECT id_employee, name, email FROM employees WHERE email != ? ORDER BY id_employee ASC");
            ps.setString(1, manager_email);           
             ResultSet res = ps.executeQuery();
            
            while (res.next()){
                employee=new Employee();
                employee.setId_employee(res.getInt("id_employee"));
                employee.setEmail(res.getString("email"));
                employee.setName(res.getString("name"));
                employees.add(employee);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return employees;
    }
    public ArrayList<String> getAllEmployees_Names(String current_user){

        employees_names = new ArrayList<String>();
        try{
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("SELECT id_employee , name FROM employees  where email != ? ORDER BY id_employee ASC");
            ps.setString(1, current_user);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                employees_id_names = res.getInt("id_employee") + ":" + res.getString("name");
                employees_names.add(employees_id_names);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees_names;
    }

    public void delete_Emp(int id ){
        try {
            cnx = myDB.getConnection();
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM employees WHERE id_employee = ?");
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
}
