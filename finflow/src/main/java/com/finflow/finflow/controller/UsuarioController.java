package com.finflow.finflow.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    // 🔥 CREATE (POST)
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    // 🔥 READ (GET)
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // 🔥 GET POR ID
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // 🔥 UPDATE (PUT)
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}