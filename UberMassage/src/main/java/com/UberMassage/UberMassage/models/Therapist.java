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

    @OneToMany(cascade=CascadeType.ALL)
    private List<Appointment> appointments;

    private Hours hoursOfOperation;

    private Gender gender;

    public Therapist() {

    }

    public Therapist(User user, Gender gender, Hours hoursOfOperation) {
        this.user = user;
        this.gender = gender;
        this.hoursOfOperation = hoursOfOperation;

    }

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
