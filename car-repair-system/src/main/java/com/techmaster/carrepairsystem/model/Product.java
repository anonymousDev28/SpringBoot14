package com.techmaster.carrepairsystem.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String color;
    private String status;
    private String condition;
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;
}
