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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//service
public class RepairService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double fee;
    private int timeRequired;

    public RepairService(String name, double fee, int timeRequired) {
        this.name = name;
        this.fee = fee;
        this.timeRequired = timeRequired;
    }
}
