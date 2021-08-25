package com.UberMassage.UberMassage.models.dto;

import com.UberMassage.UberMassage.models.Appointment;

public class RegisterFormDTO extends LoginFormDTO{

    private String verifyPassword;

    private String firstName;
    private String lastName;

    private String phoneNumber;
    private String email;

    private String state;
    private String city;

    private Appointment appointment;

    private int test = 1;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getFirstName() {
        return this.firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(
            Appointment appointment) {
        this.appointment = appointment;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
