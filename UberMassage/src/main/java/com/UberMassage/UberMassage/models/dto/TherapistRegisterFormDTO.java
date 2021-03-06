package com.UberMassage.UberMassage.models.dto;

import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.Gender;
import com.UberMassage.UberMassage.models.Hours;
import com.UberMassage.UberMassage.models.User;

import javax.persistence.OneToOne;

public class TherapistRegisterFormDTO {

    private User user;

    private String typeOfMassage;

    private String citiesWillTravel;

    private double costOfService;

    private Appointment appointment;

    private Hours hoursOfOperation;

    private Gender gender;

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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Hours getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(
            Hours hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
