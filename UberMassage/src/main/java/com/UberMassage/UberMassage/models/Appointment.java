package com.UberMassage.UberMassage.models;

import javax.persistence.Entity;

@Entity
public class Appointment extends AbstractID {

    public Appointment() {
    }

    public String youHaveAppointment() {
        return "The appointment";
    }
}
