package com.elhefny.askdoctor2.Patients;

import java.io.Serializable;

public class Patient implements Serializable {
    private String email, name, country, gender,patientImageURL,phone;
    private int age;

    public Patient() {
    }

    public Patient(String email, String name, String country, String gender, String patientImageURL, int age,String phone) {
        this.email = email;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.patientImageURL = patientImageURL;
        this.age = age;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPatientImageURL() {
        return patientImageURL;
    }
}
