package com.doacao.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaldoResponseDTO {
    private BigDecimal balance;
}
