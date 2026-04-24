package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.MetaRequest;
import com.finflow.finflow.dto.MetaResponse;
import com.finflow.finflow.model.Meta;
import com.finflow.finflow.repository.MetaRepository;
import com.finflow.finflow.mapper.MetaMapper;

@Service
public class MetaService {

    private final MetaRepository repository;

    public MetaService(MetaRepository repository) {
        this.repository = repository;
    }

    public MetaResponse criar(MetaRequest request) {
        Meta meta = MetaMapper.toEntity(request);
        return MetaMapper.toResponse(repository.save(meta));
    }

    public List<MetaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(MetaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MetaResponse buscarPorId(Long id) {
        Meta meta = repository.findById(id).orElseThrow();
        return MetaMapper.toResponse(meta);
    }

    public MetaResponse atualizar(Long id, MetaRequest request) {
        Meta meta = repository.findById(id).orElseThrow();

        meta.setDescricao(request.getDescricao());
        meta.setValorObjetivo(request.getValorObjetivo());

        return MetaMapper.toResponse(repository.save(meta));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}