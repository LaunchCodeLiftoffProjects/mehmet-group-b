package com.UberMassage.UberMassage.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class State {
    @Id
    @GeneratedValue
    private int id;


    private String state;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

    public State(){}
    public State(String state) {
        this.state = state;
//        this.cities = cities;

    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStateName(String state) {
        this.state = state;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}