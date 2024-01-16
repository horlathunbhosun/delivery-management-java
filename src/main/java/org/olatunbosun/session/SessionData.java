package org.olatunbosun.session;

public class SessionData {
    // Add fields to store user-specific session data
//    private final String someData;


    private  String email;

    private int userId;

    private String role;


    // Add constructor to set the user-specific session data

    public String getEmail(){
        return email;
    }

    public int getUserId(){
        return userId;
    }

    public String getRole(){
        return role;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setRole(String role){
        this.role = role;
    }

    //toString method
    @Override
    public String toString(){
        return "Email: " + email + "\nUserId: " + userId + "\nRole: " + role;
    }

}
