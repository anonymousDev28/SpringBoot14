package com.techmaster.carrepairsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WalletDTO {
    @NotEmpty(message = "account number không được null")
    private long account_number;
//    private int balance;
}
