package com.example.demo.controller;

import com.example.demo.service.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/import")
public class ImportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);
    @Autowired
    private ImportService importService;

    @PostMapping
    public Object importMuniData(){
        LOGGER.info("Start municipality import");
        return importService.importMuni();
    }
    @PostMapping("/brgy")
    public Object importBrgyData(){
        LOGGER.info("Start Brgy import");
        return importService.importBrgy();
    }

    @PostMapping("/resident")
    public Object importResidentData(){
        LOGGER.info("Start Resident import");
        return importService.importResident();
    }

    @GetMapping("/muni")
    public ResponseEntity<Object> getAllMunicipality(){
        LOGGER.info("Start get all municipality");
        return ResponseEntity.status(HttpStatus.OK).body(importService.getAllMunicipality());
    }

    @GetMapping("/brgy")
    public ResponseEntity<Object> getAllBrgy(){
        LOGGER.info("Start get all barangay");
        return ResponseEntity.status(HttpStatus.OK).body(importService.getAllBarangay());
    }

    @GetMapping("/resident")
    public ResponseEntity<Object> getAllResident(){
        LOGGER.info("Start get all resident");
        return ResponseEntity.status(HttpStatus.OK).body(importService.getAllResident());
    }

}