package org.olatunbosun.session;

public class SessionData {
    // Add fields to store user-specific session data
//    private final String someData;


    private String fullName;

    private  String email;

    private int userId;

    private String role;


    private String phoneNumber;
    private String truckNumber;
    private String truckCapacity;


    // Add constructor to set the user-specific session data


    public String getEmail(){
        return email;
    }

    public String getFullName(){
        return fullName;
    }


    public int getUserId(){
        return userId;
    }

    public String getRole(){
        return role;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getTruckNumber(){
        return truckNumber;
    }

    public String getTruckCapacity(){
        return truckCapacity;
    }


    public void setFullName(String fullName){
        this.fullName = fullName;
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

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setTruckNumber(String truckNumber){
        this.truckNumber = truckNumber;
    }

    public void setTruckCapacity(String truckCapacity){
        this.truckCapacity = truckCapacity;
    }






    //toString method
    @Override
    public String toString() {
        return "[" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", truckNumber='" + truckNumber + '\'' +
                ", truckCapacity='" + truckCapacity + '\'' +
                ']';
    }
}
