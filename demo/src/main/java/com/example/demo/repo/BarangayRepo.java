package com.example.demo.repo;

import com.example.demo.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangayRepo extends JpaRepository<Barangay,Integer> {

}
