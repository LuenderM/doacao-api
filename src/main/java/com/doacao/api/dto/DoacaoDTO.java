package com.doacao.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoacaoDTO {
    private Long id;
    private Double valor;
    private String status;
    private LocalDateTime dataCriacao;
}
