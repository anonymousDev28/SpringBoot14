package com.techmaster.carrepairsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@ToString
public class DemandDTO {
    // thong tin khach hang
    private CustomerDTO customerDTO;
    // thong tin can de len don ?
    // ten khach hang
    // so dien thoai
    // so du tai khoan
    // thong tin san pham
    private Set<ProductDTO> productDTOs;
}
