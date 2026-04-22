package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.ReceitaRequest;
import com.finflow.finflow.dto.ReceitaResponse;
import com.finflow.finflow.model.Receita;
import com.finflow.finflow.repository.ReceitaRepository;
import com.finflow.finflow.mapper.ReceitaMapper;

/**
 * RF03 – Registro de Receitas
 * 
 * Esta classe implementa o gerenciamento de receitas do sistema.
 * 
 * Funcionalidades:
 * - Criar receita
 * - Listar receitas
 * - Buscar receita por ID
 * - Atualizar receita
 * - Deletar receita
 */

@Service
public class ReceitaService {

    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    /**
     * RF03 – Criar receita
     */
    public ReceitaResponse criar(ReceitaRequest request) {
        Receita receita = ReceitaMapper.toEntity(request);
        return ReceitaMapper.toResponse(repository.save(receita));
    }

    /**
     * RF03 – Listar receitas
     */
    public List<ReceitaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(ReceitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * RF03 – Buscar receita por ID
     */
    public ReceitaResponse buscarPorId(Long id) {
        Receita receita = repository.findById(id).orElseThrow();
        return ReceitaMapper.toResponse(receita);
    }

    /**
     * RF03 – Atualizar receita
     */
    public ReceitaResponse atualizar(Long id, ReceitaRequest request) {
        Receita receita = repository.findById(id).orElseThrow();

        receita.setDescricao(request.getDescricao());
        receita.setValor(request.getValor());
        receita.setData(request.getData());

        return ReceitaMapper.toResponse(repository.save(receita));
    }

    /**
     * RF03 – Remover receita
     */
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
