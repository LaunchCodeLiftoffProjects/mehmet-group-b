package com.UberMassage.UberMassage.models.dto;

import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;

public class AppointmentDTO {
private Therapist therapist;
private User user;

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
