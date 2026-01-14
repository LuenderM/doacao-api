package com.doacao.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    private BigDecimal value;
    
    @JsonProperty("bankAccount")
    private BankAccountDTO bankAccount;
}
