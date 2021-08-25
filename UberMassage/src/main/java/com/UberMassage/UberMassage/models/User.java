package com.UberMassage.UberMassage.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
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
        this.test += 9;
    }
}
