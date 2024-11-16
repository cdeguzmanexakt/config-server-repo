package com.example.demo.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.LocationStats;
import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;

@Service
public class ResidentLocService {

    private final ResidentRepository residentRepository;
    DecimalFormat dec = new DecimalFormat("#0.00");

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

    public LocationStats findVbTrueResByBrgy(String brgyCode) {
    	
        return buildLocStats(residentRepository.countTotalByBrgy(brgyCode), 
        		residentRepository.countTotalVbByBrgy(brgyCode));

    }
    
    public LocationStats getOverAllStats() {
    	
    	return buildLocStats(residentRepository.countTotal(), 
        		residentRepository.countTotalVb());
    }

    public LocationStats findVbTrueResByMuni(String muniCode) {

        return buildLocStats(residentRepository.countTotalByMuni(muniCode), 
        		residentRepository.countTotalVbByMuni(muniCode));
    }
    
    private LocationStats buildLocStats(Integer totalRes, Integer totalVb) {
    	String percentage = "0%";
    	if(totalRes != null && totalRes != 0) {
    		Double perc =  (((double) totalVb/(double) totalRes)*100);
    		percentage = dec.format(perc) + "%";
    	}
    	
        LocationStats stats = new LocationStats(totalRes,totalVb,percentage);
        return stats;
    }
}




