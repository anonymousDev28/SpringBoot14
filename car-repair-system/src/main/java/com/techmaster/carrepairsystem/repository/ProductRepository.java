package com.techmaster.carrepairsystem.repository;

import com.techmaster.carrepairsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
