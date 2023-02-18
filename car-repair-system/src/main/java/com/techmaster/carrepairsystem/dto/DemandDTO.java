package com.techmaster.carrepairsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@ToString
public class DemandDTO {
    // thong tin khach hang
    private CustomerDTO customerDTO;
    // thong tin can de len don ?
    private Set<ProductDTO> productDTOs;
    // ngay tao don
    private LocalDate timeOrder;
    private String note;

}
