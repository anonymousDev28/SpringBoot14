package com.techmaster.carrepairsystem.repository;

import com.techmaster.carrepairsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
}
