package com.finflow.finflow.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.model.LogSistema;
import com.finflow.finflow.repository.LogSistemaRepository;
import com.finflow.finflow.dto.LogSistemaResponse;
import com.finflow.finflow.mapper.LogSistemaMapper;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/logs")
public class LogSistemaController {

    private final LogSistemaRepository repository;

    public LogSistemaController(LogSistemaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<LogSistemaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(LogSistemaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LogSistemaResponse buscarPorId(@PathVariable Long id) {
        LogSistema log = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log não encontrado"));

        return LogSistemaMapper.toResponse(log);
    }
}
