package com.elhefny.askdoctor2.Doctors;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String email, name, department, country, gender, doctorImageURL = "",phone;
    private double ratingValue;
    private int age, numOfPersonsWhoRate, yearsOfExperience;

    public Doctor(String email, String name, String department, String country, String gender, String doctorImageURL, double ratingValue, int age, int numOfPersonsWhoRate, int yearsOfExperience,String phone) {
        this.email = email;
        this.name = name;
        this.department = department;
        this.country = country;
        this.gender = gender;
        this.doctorImageURL = doctorImageURL;
        this.ratingValue = ratingValue;
        this.age = age;
        this.numOfPersonsWhoRate = numOfPersonsWhoRate;
        this.yearsOfExperience = yearsOfExperience;
        this.phone = phone;
    }

    public Doctor() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public String getDoctorImageURL() {
        return doctorImageURL;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public int getAge() {
        return age;
    }

    public int getNumOfPersonsWhoRate() {
        return numOfPersonsWhoRate;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getPhone() {
        return phone;
    }
}
