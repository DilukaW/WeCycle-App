package com.example.wecycle.model;

public class Student {

    private String stuID;
    private String nic;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String address;
    private String mobNumber;
    private String email;
    private String userName;
    private String status;

    public Student(String stuID, String nic, String firstName, String lastName, String mobNumber, String email, String status) {
        this.stuID = stuID;
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobNumber = mobNumber;
        this.email = email;
        this.status = status;
    }

    public Student(String stuID, String nic, String firstName, String lastName, String gender, String dob, String address, String mobNumber, String email, String status) {
        this.stuID = stuID;
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.mobNumber = mobNumber;
        this.email = email;
        this.status = status;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
