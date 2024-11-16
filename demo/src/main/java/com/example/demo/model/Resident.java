package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@Entity
@Data
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;
    @JsonProperty
    private String mobileNum;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String middleName;
    @JsonProperty
    private int age;
    @JsonProperty
    private String gender;
    @JsonProperty
    private boolean isVoter;
    @JsonProperty
    private boolean vbFlag;
    @JsonProperty
    private String brgyCode;
    @JsonProperty
    private String muniCode;

    public Resident buildResident(Row row){
        Resident resident = new Resident();
        BigDecimal mobile = BigDecimal.valueOf(row.getCell(0).getNumericCellValue());
        BigDecimal muniCode = BigDecimal.valueOf((long) row.getCell(9).getNumericCellValue(),0);
        resident.setMobileNum(mobile.toPlainString());
        resident.setFirstName(row.getCell(1).getStringCellValue());
        resident.setLastName(row.getCell(2).getStringCellValue());
            resident.setMiddleName(row.getCell(3) == null ?
                    "" : row.getCell(3).getStringCellValue());
        resident.setAge((int) row.getCell(4).getNumericCellValue());
        resident.setVoter(row.getCell(6).getBooleanCellValue());
        resident.setGender(row.getCell(5).getStringCellValue());
        resident.setBrgyCode(row.getCell(8).getStringCellValue());
        resident.setVbFlag(row.getCell(7).getBooleanCellValue());
        resident.setMuniCode(muniCode.toPlainString());
        return resident;
    }
}
