package com.doacao.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankAccountDTO {
    @JsonProperty("bank")
    private String bank;
    
    @JsonProperty("accountName")
    private String accountName;
    
    @JsonProperty("ownerName")
    private String ownerName;
    
    @JsonProperty("ownerBirthDate")
    private String ownerBirthDate;
    
    @JsonProperty("cpfCnpj")
    private String cpfCnpj;
    
    @JsonProperty("agency")
    private String agency;
    
    @JsonProperty("account")
    private String account;
    
    @JsonProperty("accountDigit")
    private String accountDigit;
    
    @JsonProperty("bankAccountType")
    private String bankAccountType;
}
