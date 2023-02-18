package com.techmaster.carrepairsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ProductDTO {
    private String name;
    private String color;
    private String status;
}
