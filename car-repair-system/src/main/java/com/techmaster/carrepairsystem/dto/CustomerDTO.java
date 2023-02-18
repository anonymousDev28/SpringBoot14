package com.techmaster.carrepairsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private int phone;
    private WalletDTO walletDTO;
}
