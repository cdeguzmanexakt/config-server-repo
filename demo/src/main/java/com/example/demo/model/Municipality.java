package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Municipality {
    @Id
    private int citymunCode;
    private String citymunDesc;
    private String provDesc;
    private int provCode;


    public String getCitymunDesc() {
        return citymunDesc;
    }

    public void setCitymunDesc(String citymunDesc) {
        this.citymunDesc = citymunDesc;
    }

    public String getProvDesc() {
        return provDesc;
    }

    public void setProvDesc(String provDesc) {
        this.provDesc = provDesc;
    }

    public int getProvCode() {
        return provCode;
    }

    public void setProvCode(int provCode) {
        this.provCode = provCode;
    }

    public int getCitymunCode() {
        return citymunCode;
    }

    public void setCitymunCode(int citymunCode) {
        this.citymunCode = citymunCode;
    }
}
