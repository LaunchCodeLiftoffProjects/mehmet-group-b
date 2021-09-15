package com.UberMassage.UberMassage.models;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NONBINARY("Non-binary");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
