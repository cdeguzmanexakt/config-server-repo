package com.example.demo.controller;

import com.example.demo.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/export")
public class ImportController {

    @Autowired
    private ImportService importService;

    @PostMapping
    public Object importMuniData(){
        return importService.importMuni();
    }

    @GetMapping
    public ResponseEntity<Object> getAllMunicipality(){
        return ResponseEntity.status(HttpStatus.OK).body(importService.getAllMunicipality());
    }

}
