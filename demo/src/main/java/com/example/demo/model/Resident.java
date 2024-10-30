package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resident {
    @Id
    private String mobileNum;
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private char gender;
    private boolean isVoter;
    private boolean vbFlag;
    private int brgyCode;
    private int muniCode;

    public int getMuniCode() {
        return muniCode;
    }

    public void setMuniCode(int muniCode) {
        this.muniCode = muniCode;
    }

    public int getBrgyCode() {
        return brgyCode;
    }

    public void setBrgyCode(int brgyCode) {
        this.brgyCode = brgyCode;
    }

    public boolean isVbFlag() {
        return vbFlag;
    }

    public void setVbFlag(boolean vbFlag) {
        this.vbFlag = vbFlag;
    }

    public boolean isVoter() {
        return isVoter;
    }

    public void setVoter(boolean voter) {
        isVoter = voter;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
}
