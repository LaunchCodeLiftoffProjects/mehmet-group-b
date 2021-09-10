package com.UberMassage.UberMassage.models;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue
    private int id;

    private String city;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private State state;

    public City(){}

    public City(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
