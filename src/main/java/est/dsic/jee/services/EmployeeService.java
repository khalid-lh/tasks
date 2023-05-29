package est.dsic.jee.services;

import est.dsic.jee.models.LoginUser;

import java.util.ArrayList;

import est.dsic.jee.dal.EmployeeDao;
import est.dsic.jee.models.Employee;



public class EmployeeService{

    private EmployeeDao employeeDao = new EmployeeDao();
    private Employee employee = new Employee();

    public String check(LoginUser login){
        this.employee=employeeDao.authentifier(login);
        if (this.employee == null) {
        }else{
            return this.employee.getFonction();
        }
        return "Not_exist";
    }

    public void add_Emp(Employee emp){
        employeeDao.add_Emp(emp);
    }

    public ArrayList<String> getAllEmployees_Names(String current_user){
        return employeeDao.getAllEmployees_Names(current_user);   
    }
    public ArrayList<Employee> getAll(String manager_email){
        return employeeDao.getAllEmployees(manager_email);
    }
    public void delete_Emp(int id ){
        employeeDao.delete_Emp(id);
    }
}
