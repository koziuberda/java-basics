package com.kpi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.List;

public class Enrollee {
    private long identificationNumber;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String phoneNumber;
    private List<Integer> grades;


    public Enrollee() {
        // The parameterless constructor is required for Jackson
    }

    public Enrollee(long identificationNumber, String name, String surname, String patronymic, String address, String phoneNumber, List<Integer> grades) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.grades = grades;
    }

    public long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    @JsonIgnore
    public double getAverageScore() {
        return grades.stream().mapToDouble(a -> a).average().orElse(0);
    }

    @JsonIgnore
    public boolean hasBadMarks() {
        return grades.stream().anyMatch(mark -> mark <= 2);
    }

    @Override
    public String toString() {
        String format = "| %15s | %15s | %15s | %20s | %18s | %21d | ";
        String personalDetails = String.format(format, name, surname, patronymic, address, phoneNumber, identificationNumber);
        String grades = this.grades.toString() + " | " + getAverageScore() + " |";
        return personalDetails + grades;
    }
}
