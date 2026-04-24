package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.UsuarioRequest;
import com.finflow.finflow.dto.UsuarioResponse;
import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.UsuarioRepository;
import com.finflow.finflow.mapper.UsuarioMapper;

/**
 * RF01 – Cadastro de Usuário
 * RF02 – Login (parcial)
 */
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final LogSistemaService logService;

    public UsuarioService(UsuarioRepository repository, LogSistemaService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    /**
     * RF01 – Criar usuário
     */
    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario user = UsuarioMapper.toEntity(request);
        Usuario salvo = repository.save(user);

        logService.registrar("USUARIO CRIADO");

        return UsuarioMapper.toResponse(salvo);
    }

    /**
     * RF01 – Listar usuários
     */
    public List<UsuarioResponse> listar() {
        logService.registrar("USUARIO LISTADO");

        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * RF01 – Buscar usuário por ID
     */
    public UsuarioResponse buscarPorId(Long id) {
        Usuario user = repository.findById(id).orElseThrow();

        logService.registrar("USUARIO BUSCADO ID " + id);

        return UsuarioMapper.toResponse(user);
    }

    /**
     * RF01 – Deletar usuário
     */
    public void deletar(Long id) {
        repository.deleteById(id);

        logService.registrar("USUARIO DELETADO ID " + id);
    }

    /**
     * RF02 – Login de usuário (implementação simples)
     * 
     * Valida email e senha informados.
     */
public UsuarioResponse login(String email, String senha) {
    Usuario user = repository.findByEmail(email);

    if (user != null && user.getSenha().equals(senha)) {

        logService.registrar("LOGIN REALIZADO: " + email);

        return UsuarioMapper.toResponse(user);
    }

    logService.registrar("LOGIN FALHOU: " + email);

    throw new RuntimeException("Credenciais inválidas");
    }

}