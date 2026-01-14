package com.doacao.api.controller;

import com.doacao.api.service.FinanceiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping("/saldo")
    public ResponseEntity<Map<String, BigDecimal>> getSaldo() {
        BigDecimal saldo = financeiroService.getSaldo();
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("saldo", saldo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saque")
    public ResponseEntity<Map<String, String>> solicitarSaque(@RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        if (valor == null || valor <= 0) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", "Valor inválido");
            return ResponseEntity.badRequest().body(error);
        }
        
        financeiroService.solicitarSaque(valor);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Saque solicitado com sucesso");
        return ResponseEntity.ok(response);
    }
}
