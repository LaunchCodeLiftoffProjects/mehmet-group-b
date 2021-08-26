package com.UberMassage.UberMassage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Therapist therapist;
    @OneToOne
    private User user;

    public Appointment(){};
//    public Appointment (Therapist therapist, User user){
//        this.therapist = therapist;
//        this.user = user;
//    }



//    public int getId() {
//        return id;
//    }
//
    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
