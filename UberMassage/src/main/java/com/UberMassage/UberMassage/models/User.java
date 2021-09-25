package com.UberMassage.UberMassage.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    private Appointment appointment;

    private int test = 1;

    public User() {

    }

    public User(String username, String password, String fName, String lName,
                String state, String city, String email, String phoneNumber) {
        super(username, password, fName, lName,
                state, city, email, phoneNumber);
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
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

    public void lucas() {
        this.appointment.getClient().getFirstName();
        this.appointment.getTherapist().getFirstName();
    }
}
