package com.finflow.finflow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.finflow.finflow.services.MetaService;
import com.finflow.finflow.dto.MetaRequest;
import com.finflow.finflow.dto.MetaResponse;

@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MetaService service;

    public MetaController(MetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MetaResponse> criar(@RequestBody MetaRequest req) {
        MetaResponse res = service.criar(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<List<MetaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody MetaRequest req) {

        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}