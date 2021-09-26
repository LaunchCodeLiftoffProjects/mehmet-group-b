package com.UberMassage.UberMassage.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public class Therapist {

    @OneToOne (mappedBy = "therapist")
    private User user;
    @Id
    @GeneratedValue
    private int therapistId;

//    private String typeOfMassage;
//
//    private String citiesWillTravel;

//    private double costOfService;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Appointment> appointments;

    private Hours hoursOfOperation;

//    private String pickedHour;

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

//    public String getTypeOfMassage() {
//        return typeOfMassage;
//    }
//
//    public void setTypeOfMassage(String typeOfMassage) {
//        this.typeOfMassage = typeOfMassage;
//    }
//
//    public String getCitiesWillTravel() {
//        return citiesWillTravel;
//    }
//
//    public void setCitiesWillTravel(String citiesWillTravel) {
//        this.citiesWillTravel = citiesWillTravel;
//    }
//
//    public double getCostOfService() {
//        return costOfService;
//    }
//
//    public void setCostOfService(double costOfService) {
//        this.costOfService = costOfService;
//    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(
            List<Appointment> appointments) {
        this.appointments = appointments;

    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
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
