package com.doacao.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private String id;
    private BigDecimal value;
    
    @JsonProperty("paymentDate")
    private LocalDateTime paymentDate;
    
    @JsonProperty("customer")
    private CustomerDTO customer;
}
