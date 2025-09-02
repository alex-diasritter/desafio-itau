package com.alex.desafio_itau.controller;
import com.alex.desafio_itau.domain.dto.TransacaoRequestDTO;
import com.alex.desafio_itau.domain.dto.TransacaoResponseDTO;
import com.alex.desafio_itau.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/transacao")
    public ResponseEntity<Void> saveTransacao(@RequestBody @Valid TransacaoRequestDTO dto) {
        service.saveTransfers(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/transacoes")
    public ResponseEntity<String> deleteTransacoes(){
        service.deleteAll();
        return ResponseEntity.ok("Todas as informações foram apagadas com sucesso");
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<TransacaoResponseDTO> estatistica(){
        var dto = service.getEstatisticas();
        return ResponseEntity.ok(dto);
    }
}
