package com.UberMassage.UberMassage.models.dto;

import com.UberMassage.UberMassage.models.User;

public class AppointmentDTO {

    private User therapist;

    private User client;

    public User getTherapist() {
        return therapist;
    }

    public void setTherapist(User therapist) {
        this.therapist = therapist;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
