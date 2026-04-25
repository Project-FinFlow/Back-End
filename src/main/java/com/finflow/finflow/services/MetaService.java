package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.MetaRequest;
import com.finflow.finflow.dto.MetaResponse;
import com.finflow.finflow.model.Meta;
import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.MetaRepository;
import com.finflow.finflow.repository.UsuarioRepository;
import com.finflow.finflow.mapper.MetaMapper;

@Service
public class MetaService {

    private final MetaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public MetaService(
            MetaRepository repository,
            UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public MetaResponse criar(MetaRequest request) {
        Meta meta = MetaMapper.toEntity(request);
        preencherRelacionamentos(meta, request);
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
        meta.setValorAtual(request.getValorAtual());
        preencherRelacionamentos(meta, request);

        return MetaMapper.toResponse(repository.save(meta));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void preencherRelacionamentos(Meta meta, MetaRequest request) {
        Usuario usuario = null;

        if (request.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        }

        meta.setUsuario(usuario);
    }
}
