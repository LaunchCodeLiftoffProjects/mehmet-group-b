package com.UberMassage.UberMassage.models.dto;

import com.UberMassage.UberMassage.models.Hours;
import com.UberMassage.UberMassage.models.User;

import javax.persistence.OneToOne;

public class TherapistRegisterFormDTO {

    private User user;

    private String typeOfMassage;

    private String citiesWillTravel;

    private double costOfService;

    private String appointment;

    private Hours hoursOfOperation;

    private String gender;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTypeOfMassage() {
        return typeOfMassage;
    }

    public void setTypeOfMassage(String typeOfMassage) {
        this.typeOfMassage = typeOfMassage;
    }

    public String getCitiesWillTravel() {
        return citiesWillTravel;
    }

    public void setCitiesWillTravel(String citiesWillTravel) {
        this.citiesWillTravel = citiesWillTravel;
    }

    public double getCostOfService() {
        return costOfService;
    }

    public void setCostOfService(double costOfService) {
        this.costOfService = costOfService;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public Hours getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(
            Hours hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
