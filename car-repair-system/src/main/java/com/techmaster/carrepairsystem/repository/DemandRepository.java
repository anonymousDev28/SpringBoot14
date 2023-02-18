package com.techmaster.carrepairsystem.repository;

import com.techmaster.carrepairsystem.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand,Integer> {
}
