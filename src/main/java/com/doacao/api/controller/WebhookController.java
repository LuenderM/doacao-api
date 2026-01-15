package com.doacao.api.controller;

import com.doacao.api.domain.Doacao;
import com.doacao.api.domain.DoacaoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final DoacaoRepository doacaoRepository;
    private final ObjectMapper objectMapper;

    public WebhookController(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/asaas")
    public ResponseEntity<String> receberWebhook(@RequestBody String payload) {
        try {
            JsonNode rootNode = objectMapper.readTree(payload);
            String event = rootNode.path("event").asText();
            if ("PAYMENT_RECEIVED".equals(event) || "PAYMENT_CONFIRMED".equals(event)) {
                
                JsonNode paymentNode = rootNode.path("payment");
                
                double valor = paymentNode.path("value").asDouble();
                String idTransacao = paymentNode.path("id").asText();
                String billingType = paymentNode.path("billingType").asText(); // PIX, BOLETO, etc

                String nomeDoador = "Doador via " + billingType;

                Doacao doacao = new Doacao();
                doacao.setValor(valor);
                doacao.setNomeDoador(nomeDoador);
                doacao.setStatus("PAGO");
                doacao.setIdTransacaoAsaas(idTransacao);
                
                doacao.setDataCriacao(LocalDateTime.now());
                
                doacao.setEmail(""); 
                doacao.setCpf("");

                doacaoRepository.save(doacao);
                System.out.println("WEBHOOK SUCESSO: Doação de R$ " + valor + " salva!");
            }

            return ResponseEntity.ok("Recebido com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Erro processado, mas recebido");
        }
    }
}