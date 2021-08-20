package com.UberMassage.UberMassage.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @OneToOne ()
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    public User() {

    }

    public User(String username, String password, String fName, String lName,
                String state, String city, String email, String phoneNumber) {
        super(username, password, fName, lName,
                state, city, email, phoneNumber);
    }




}
