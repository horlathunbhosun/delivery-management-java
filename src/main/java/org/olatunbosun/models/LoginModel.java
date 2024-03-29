package org.olatunbosun.models;

public class LoginModel {

    private  String email;
    private  String password;

    public LoginModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    //toString method
    @Override
    public String toString(){
        return "Email: " + email + "\nPassword: " + password;
    }

}
