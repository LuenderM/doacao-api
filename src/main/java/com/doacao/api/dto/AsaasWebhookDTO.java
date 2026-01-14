package com.doacao.api.dto;

import lombok.Data;

@Data
public class AsaasWebhookDTO {
    private String event;
    private PaymentDTO payment;
    private ChargeDTO charge;
}
