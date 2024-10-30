package com.example.demo.repo;

import com.example.demo.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,String> {

    List<Resident> findByBrgyCode(int brgyCode);
}
