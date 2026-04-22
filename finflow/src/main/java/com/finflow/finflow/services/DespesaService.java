package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.DespesaRequest;
import com.finflow.finflow.dto.DespesaResponse;
import com.finflow.finflow.model.Despesa;
import com.finflow.finflow.repository.DespesaRepository;
import com.finflow.finflow.mapper.DespesaMapper;

/**
 * RF04 – Registro de Despesas
 * 
 * Esta classe implementa as funcionalidades de gerenciamento de despesas.
 * 
 * Funcionalidades:
 * - Criar despesa
 * - Listar despesas
 * - Buscar despesa por ID
 * - Atualizar despesa
 * - Deletar despesa
 * 
 * Regras:
 * - A despesa deve possuir valor, data e descrição
 */

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }
     
    /**
     * RF04 – Criar despesa
     */
    public DespesaResponse criar(DespesaRequest request) {
        Despesa despesa = DespesaMapper.toEntity(request);
        return DespesaMapper.toResponse(repository.save(despesa));
    }

    /**
     * RF04 – Listar despesas
     */
    public List<DespesaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(DespesaMapper::toResponse)
                .collect(Collectors.toList());
    }
     /**
     * RF04 – Buscar despesa por ID
     */
    public DespesaResponse buscarPorId(Long id) {
        Despesa despesa = repository.findById(id).orElseThrow();
        return DespesaMapper.toResponse(despesa);
    }

    /**
     * RF04 – Atualizar despesa
     */
    public DespesaResponse atualizar(Long id, DespesaRequest request) {
        Despesa despesa = repository.findById(id).orElseThrow();

        despesa.setDescricao(request.getDescricao());
        despesa.setValor(request.getValor());
        despesa.setData(request.getData());

        return DespesaMapper.toResponse(repository.save(despesa));
    }

    /**
     * RF04 – Remover despesa
     */
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
