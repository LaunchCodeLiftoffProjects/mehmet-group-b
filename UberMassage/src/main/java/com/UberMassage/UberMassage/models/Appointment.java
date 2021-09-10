package com.UberMassage.UberMassage.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Optional;

@Entity
public class Appointment extends AbstractID {

    @OneToOne
    User therapist;

    @OneToOne
    User client;

    public Appointment() {};

    public Appointment(User therapist, User client) {
        this.therapist = therapist;
        this.client = client;
    }

    public String youHaveAppointment() {
        return "The appointment";
    }

    public User getTherapist() {
        return therapist;
    }

    public void setTherapist(User therapist) {
        this.therapist = therapist;
    }

    public void lucasLawrence() {
        this.client.getFirstName();
        this.therapist.getFirstName();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
