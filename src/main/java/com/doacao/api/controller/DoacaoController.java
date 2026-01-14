package com.doacao.api.controller;

import com.doacao.api.domain.Doacao;
import com.doacao.api.domain.DoacaoRepository;
import com.doacao.api.dto.DoacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    private final DoacaoRepository doacaoRepository;

    public DoacaoController(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> listarDoacoes() {
        List<Doacao> doacoes = doacaoRepository.findAll();
        
        List<DoacaoDTO> doacoesDTO = doacoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(doacoesDTO);
    }

    private DoacaoDTO converterParaDTO(Doacao doacao) {
        DoacaoDTO dto = new DoacaoDTO();
        dto.setId(doacao.getId());
        dto.setValor(doacao.getValor());
        dto.setStatus(doacao.getStatus());
        dto.setDataCriacao(doacao.getDataCriacao());
        return dto;
    }
}
