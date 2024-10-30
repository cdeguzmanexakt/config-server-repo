package com.example.demo.controller;

import com.example.demo.model.Resident;
import com.example.demo.service.ResidentLocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/resident")
public class ResidentController {
    @Autowired
    private ResidentLocService resLocService;


    @GetMapping("/{brgyId}")
    public List<Resident> findAllResByBrgy(@PathVariable("brgyId") int brgyId){
        return resLocService.findResByBrgy(brgyId);
    }
    @PostMapping
    public Object importResidentData(){
        return resLocService.importResident();
    }
}
