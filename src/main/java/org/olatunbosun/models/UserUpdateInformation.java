package org.olatunbosun.models;

public class UserUpdateInformation {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private String truckNumber;
    private String truckCapacity;



public UserUpdateInformation(String fullName, String email, String phoneNumber, String role, String truckNumber, String truckCapacity) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.truckNumber = truckNumber;
        this.truckCapacity = truckCapacity;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckCapacity() {
        return truckCapacity;
    }

    public void setTruckCapacity(String truckCapacity) {
        this.truckCapacity = truckCapacity;
    }


    @Override
    public String toString() {
        return "UserUpdateInformation{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", truckNumber='" + truckNumber + '\'' +
                ", truckCapacity='" + truckCapacity + '\'' +
                '}';
    }
}
