package com.UberMassage.UberMassage.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Optional;

@Entity
public class Appointment extends AbstractID {

    @OneToOne
    User therapist;

    @OneToOne
    User client;

    int pickedTime;



    public Appointment() {};

    public Appointment(User therapist, User client, int pickedTime) {
        this.therapist = therapist;
        this.client = client;
        this.pickedTime = pickedTime;
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

    public int getPickedTime() {
        return pickedTime;
    }

    public void setPickedTime(int pickedTime) {
        this.pickedTime = pickedTime;
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

    public String timeConverter() {
        String result;
        if (this.pickedTime == 0) {
            result = "12 am";
        }
        else if (this.pickedTime == 12) {
            result = "12 pm";
        }
        else if (this.pickedTime > 12) {
            this.pickedTime = this.pickedTime - 12;
            result = String.valueOf(this.pickedTime) + " pm";
        } else {
            result = String.valueOf(this.pickedTime) + " am";
        }

        return result;
    }
}
