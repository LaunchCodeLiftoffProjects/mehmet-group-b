package com.UberMassage.UberMassage.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractID {

    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }
}
