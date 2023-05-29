package est.dsic.jee.controllers;




import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;


import est.dsic.jee.errors.Errors;
import est.dsic.jee.models.LoginUser;
import est.dsic.jee.services.EmployeeService;

import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;

@Model
public class LoginController{
    
    @Inject
    ExternalContext extCtx;
    @Inject
    FacesContext facesCtx;
    @Inject
    @ManagedProperty(value = "#{loginUser}")
    private  LoginUser loginuser;
    
    //public static String curent_user= loginuser.getEmail_user();
    EmployeeService employeeService = new EmployeeService();

 

    public String Logout(){
        System.out.println("fin"+loginuser.getEmail_user());
        extCtx.invalidateSession();
        
        return "/Login/authentification";
    }

    public String login(){
        if (loginuser.getEmail_user().equals("") || loginuser.getPwd_user().equals("")) {
            
            FacesMessage fcsMsg = new FacesMessage(Errors.fields_vide);
            facesCtx.addMessage("form_login:login", fcsMsg);
            
            return "";
        }else{
            if(employeeService.check(loginuser).equals("manager")){
                System.out.println(loginuser.getEmail_user());
                return "/Manager/manager_interface.xhtml?faces-redirect=true";
            }else if (employeeService.check(loginuser).equals("employee")){
                return "/Employee/employee_interface.xhtml?faces-redirect=true";
            }else if(employeeService.check(loginuser).equals("Not_exist")){
                FacesMessage fcsMsg = new FacesMessage(Errors.user_not_exist);
                facesCtx.addMessage("form_login:login", fcsMsg); 
                return "";
            }
        }
        return "";
    }
   
    public  String getcurrentuser(){

        return loginuser.getEmail_user();
    }

     
    
}

