package com.techmaster.carrepairsystem.repository;


import com.techmaster.carrepairsystem.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
