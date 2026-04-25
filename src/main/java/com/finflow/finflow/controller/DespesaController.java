package com.finflow.finflow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.finflow.finflow.services.DespesaService;
import com.finflow.finflow.dto.DespesaRequest;
import com.finflow.finflow.dto.DespesaResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    
    @PostMapping
    public ResponseEntity<DespesaResponse> criar(@Valid @RequestBody DespesaRequest req) {
        DespesaResponse res = service.criar(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
}

    @GetMapping
    public ResponseEntity<List<DespesaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody DespesaRequest req) {

        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
