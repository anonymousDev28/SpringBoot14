package com.techmaster.carrepairsystem.repository;

import com.techmaster.carrepairsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
}
