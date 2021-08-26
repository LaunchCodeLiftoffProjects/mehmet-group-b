package com.UberMassage.UberMassage.models;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "therapist")
    private Appointment appointment;

    private Hours hoursOfOperation;

    private Gender gender;



    public Therapist() {

    }

    public Therapist(User user, Gender gender, Hours hoursOfOperation) {
        this.user = user;
        this.gender = gender;
        this.hoursOfOperation = hoursOfOperation;

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getTherapistId() {
        return therapistId;
    }

    public void setTherapistId(int therapistId) {
        this.therapistId = therapistId;
    }

    public void genderChange(Gender genderTest, String test) {
        Gender result = genderTest;
        test.toUpperCase();
    }
}
