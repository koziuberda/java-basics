package com.kpi.model.entities;

import java.util.Arrays;

public class Enrollee {
    private long identificationNumber;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String phoneNumber;
    private int[] grades;


    public Enrollee() {
        // The parameterless constructor is required for Jackson
    }

    public Enrollee(long identificationNumber, String name, String surname, String patronymic, String address, String phoneNumber, int[] grades) {
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

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        String format = "name = %s | surname = %s | " +
                " patronymic = %s | address = %s | " +
                "phoneNumber = %s | identificationNumber = %d | ";
        String personalDetails = String.format(format, name, surname, patronymic, address, phoneNumber, identificationNumber);
        String grades = "grades: " + Arrays.toString(this.grades);
        return personalDetails + grades;
    }
}
