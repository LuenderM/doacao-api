package com.doacao.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeDTO {
    private String id;
    private BigDecimal value;
    
    @JsonProperty("customer")
    private CustomerDTO customer;
}
