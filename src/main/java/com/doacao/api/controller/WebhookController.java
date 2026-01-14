package com.doacao.api.controller;

import com.doacao.api.domain.Doacao;
import com.doacao.api.domain.DoacaoRepository;
import com.doacao.api.dto.AsaasWebhookDTO;
import com.doacao.api.dto.CustomerDTO;
import com.doacao.api.dto.PaymentDTO;
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

    public WebhookController(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    @PostMapping("/asaas")
    public ResponseEntity<Void> receberWebhook(@RequestBody AsaasWebhookDTO webhook) {
        if ("PAYMENT_RECEIVED".equals(webhook.getEvent())) {
            PaymentDTO payment = webhook.getPayment();
            
            if (payment != null) {
                Double valor = payment.getValue() != null ? payment.getValue().doubleValue() : null;
                LocalDateTime dataPagamento = payment.getPaymentDate();
                String idTransacao = payment.getId();
                
                String nomeDoador = "Anônimo";
                CustomerDTO customer = payment.getCustomer();
                if (customer != null && customer.getName() != null && !customer.getName().trim().isEmpty()) {
                    nomeDoador = customer.getName();
                }
                
                if (valor != null && dataPagamento != null) {
                    Doacao doacao = new Doacao();
                    doacao.setValor(valor);
                    doacao.setNomeDoador(nomeDoador);
                    doacao.setEmail("");
                    doacao.setCpf("");
                    doacao.setStatus("PAGO");
                    doacao.setIdTransacaoAsaas(idTransacao);
                    doacao.setDataCriacao(dataPagamento);
                    
                    doacaoRepository.save(doacao);
                }
            }
        }
        
        return ResponseEntity.ok().build();
    }
}
