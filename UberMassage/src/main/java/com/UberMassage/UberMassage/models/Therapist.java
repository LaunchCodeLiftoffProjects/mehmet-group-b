package com.UberMassage.UberMassage.models;

import java.util.HashMap;

public class Therapist extends AbstractEntity{

    private String typeOfMassage;

    private String citiesWillTravel;

    private HashMap<String, Double> costOfService;

    private String appointment;

    private Hours hoursOfOperation;

    private String gender;

    public Therapist() {

    }

    public Therapist(String typeOfMassage) {
        this.typeOfMassage = typeOfMassage;
    }
}
