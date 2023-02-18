package com.techmaster.carrepairsystem.repository;

import com.techmaster.carrepairsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByPhone(int phone);
}
