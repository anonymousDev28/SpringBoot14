package com.techmaster.carrepairsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//order
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    // use many to one or many to many ?
    @OneToMany(mappedBy = "demand")
    private Set<Staff> staffs;
    @OneToMany(mappedBy = "demand")
    private Set<Product> products;
    //use many to many
    @ManyToMany
    @JoinTable(
            name = "repairServices_demands",
            joinColumns = @JoinColumn(name = "Demand_id"),
            inverseJoinColumns = @JoinColumn(name = "RepairService_id")
    )
    private Set<RepairService> repairServices;
    private String note;
    private LocalDate timeOrder;
    private LocalDate timeEstimate;
    private String status;
    private double totalPrice;
}
