package com.UberMassage.UberMassage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashMap;

@Entity
public class Therapist {



    @OneToOne (mappedBy = "therapist")
    private User user;
    @Id
    @GeneratedValue
    private int therapistId;

    private String typeOfMassage;

    private String citiesWillTravel;

    private double costOfService;

    private String appointment;

    //private Hours hoursOfOperation;

    private String gender;

    public Therapist() {

    }

    public Therapist(User user,String gender) {
        this.user =user;
        this.gender = gender;
    }

//    public Therapist(User user, String typeOfMassage, double costOfService,
//                     String gender) {
//
//        this.user = user;
//        this.typeOfMassage = typeOfMassage;
//        this.costOfService = costOfService;
//        this.gender = gender;
//    }

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

//    public Hours getHoursOfOperation() {
//        return hoursOfOperation;
//    }
//
//    public void setHoursOfOperation(
//            Hours hoursOfOperation) {
//        this.hoursOfOperation = hoursOfOperation;
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
