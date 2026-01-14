package com.doacao.api.service;

import com.doacao.api.dto.BankAccountDTO;
import com.doacao.api.dto.SaldoResponseDTO;
import com.doacao.api.dto.TransferRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
public class FinanceiroService {

    private final RestClient restClient;
    private final String asaasBaseUrl;

    @Value("${asaas.api.key}")
    private String asaasApiKey;

    @Value("${asaas.transfer.bank}")
    private String transferBank;

    @Value("${asaas.transfer.accountName}")
    private String transferAccountName;

    @Value("${asaas.transfer.ownerName}")
    private String transferOwnerName;

    @Value("${asaas.transfer.ownerBirthDate}")
    private String transferOwnerBirthDate;

    @Value("${asaas.transfer.cpfCnpj}")
    private String transferCpfCnpj;

    @Value("${asaas.transfer.agency}")
    private String transferAgency;

    @Value("${asaas.transfer.account}")
    private String transferAccount;

    @Value("${asaas.transfer.accountDigit}")
    private String transferAccountDigit;

    @Value("${asaas.transfer.bankAccountType}")
    private String transferBankAccountType;

    public FinanceiroService(@Value("${asaas.base.url:https://sandbox.asaas.com}") String asaasBaseUrl) {
        this.asaasBaseUrl = asaasBaseUrl;
        this.restClient = RestClient.builder()
                .baseUrl(asaasBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public BigDecimal getSaldo() {
        SaldoResponseDTO response = restClient.get()
                .uri("/api/v3/finance/balance")
                .header("access_token", asaasApiKey)
                .retrieve()
                .body(SaldoResponseDTO.class);

        return response != null ? response.getBalance() : BigDecimal.ZERO;
    }

    public void solicitarSaque(double valor) {
        BankAccountDTO bankAccount = new BankAccountDTO();
        bankAccount.setBank(transferBank);
        bankAccount.setAccountName(transferAccountName);
        bankAccount.setOwnerName(transferOwnerName);
        bankAccount.setOwnerBirthDate(transferOwnerBirthDate);
        bankAccount.setCpfCnpj(transferCpfCnpj);
        bankAccount.setAgency(transferAgency);
        bankAccount.setAccount(transferAccount);
        bankAccount.setAccountDigit(transferAccountDigit);
        bankAccount.setBankAccountType(transferBankAccountType);

        TransferRequestDTO request = new TransferRequestDTO();
        request.setValue(BigDecimal.valueOf(valor));
        request.setBankAccount(bankAccount);

        restClient.post()
                .uri("/api/v3/transfers")
                .header("access_token", asaasApiKey)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
