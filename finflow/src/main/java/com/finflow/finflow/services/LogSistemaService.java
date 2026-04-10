package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.finflow.finflow.model.LogSistema;
import com.finflow.finflow.repository.LogSistemaRepository;

@Service
public class LogSistemaService {

    private final LogSistemaRepository repository;

    public LogSistemaService(LogSistemaRepository repository) {
        this.repository = repository;
    }

    public void registrar(String acao) {
        LogSistema log = new LogSistema();
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now());
        repository.save(log);
    }
}