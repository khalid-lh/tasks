package est.dsic.jee.models;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("loginUser")
@SessionScoped
public class LoginUser implements Serializable{

    private String email_user;
    private String pwd_user;

    public LoginUser(){
    }



    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }



    public String getPwd_user() {
        return pwd_user;
    }



    public void setPwd_user(String pwd_user) {
        this.pwd_user = pwd_user;
    }
    
    
    
}
