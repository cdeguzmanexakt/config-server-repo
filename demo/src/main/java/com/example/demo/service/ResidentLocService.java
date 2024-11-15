package com.example.demo.service;

import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ResidentLocService {

    private final ResidentRepository residentRepository;

    public ResidentLocService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<Resident> findAllByBrgyCode(String brgyCode) {
        return residentRepository.findByBrgyCode(brgyCode);
    }

    public List<Resident> findAllByBrgyCode2(String brgyCode) {
        return residentRepository.findByLikeBrgyCode(brgyCode);
    }

    public List<Resident> findAllByMuniCode(String muniCode) {
        return residentRepository.findByMuniCode(muniCode);
    }

    public List<Resident> findAllByMuniCode2(String muniCode) {
        return residentRepository.findByLikeMuniCode(muniCode);
    }

    public Integer findVbTrueResByBrgy(String brgyCode) {
        return residentRepository.countResidentVbTrue(brgyCode);
    }

    public List<Integer> findVbTrueResByMuni(String muniCode) {
//        List<?> list = Arrays.asList(residentRepository.countResidentVbTrueByMuni(muniCode));
//        for (Object test:list){
//            System.out.println(test[0]);
//        }
        List<Integer> list = new ArrayList<>();
        for (Integer x : residentRepository.countResidentVbTrueByMuni(muniCode)){
            System.out.println(x);
            list.add(x);
        }

        return list;
    }
}




