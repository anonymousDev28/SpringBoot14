package com.techmaster.carrepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String color;
    private String status;
//    private String description;
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;

    public Product(int id) {
        this.id = id;
    }

}
