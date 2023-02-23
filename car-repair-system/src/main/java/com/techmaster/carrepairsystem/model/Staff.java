package com.techmaster.carrepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;
    public Staff(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
//
//    public String rank;
}
