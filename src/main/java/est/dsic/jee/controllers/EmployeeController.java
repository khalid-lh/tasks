package est.dsic.jee.controllers;


import java.util.ArrayList;

import est.dsic.jee.errors.Errors;
import est.dsic.jee.models.Employee;
import est.dsic.jee.models.LoginUser;
import est.dsic.jee.services.EmployeeService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;


@Model
public class EmployeeController{

    @Inject
    FacesContext facesCtx;
    @Inject
    @ManagedProperty(value = "#{employee}")
    Employee employee;
    @Inject
    @ManagedProperty(value = "#{loginUser}")
    private LoginUser loginuser;
    EmployeeService employeeService = new EmployeeService(); 
    private static int activeTabIndex = 0; // set default active tab index to 0
    String errorMessage =null;
    
    



    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static void setActiveTabIndex(int index) {
          activeTabIndex = index;
    }

    public int getActiveTabIndex() {
        return activeTabIndex;
    }



    public String add_Emp(){
        if(employee.getName().equals("") || employee.getEmail().equals("") || employee.getPwd().equals("")){
            FacesMessage fcsMsg = new FacesMessage(Errors.fields_vide);
             FacesContext.getCurrentInstance().addMessage("form_user:msg_user", fcsMsg);
          
            return null;
        }else{
            employee.setFonction("employee");
            employeeService.add_Emp(employee);
            this.employee.setEmail(null);
            this.employee.setName(null);
            this.employee.setFonction(null);
            this.employee.setId_employee(0);
            this.employee.setPwd(null);
            setActiveTabIndex(2);
            return "Manager/manager_interface";
        }
    
    }

    public  ArrayList<String> getAllemployees_names(){
      return  employeeService.getAllEmployees_Names(loginuser.getEmail_user());
    }
    public  ArrayList<Employee> getAllemployees(){
        return  employeeService.getAll(loginuser.getEmail_user());
      }

      public String delete_Emp(int id){
            employeeService.delete_Emp(id);
            setActiveTabIndex(2);
            return "Manager/manager_interface";
      }
}
