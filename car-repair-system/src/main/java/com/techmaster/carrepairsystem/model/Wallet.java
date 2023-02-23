package com.techmaster.carrepairsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_num;
    private int balance;

    public Wallet(int balance) {
        this.balance = balance;
    }
}
