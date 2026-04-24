package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.CategoriaRequest;
import com.finflow.finflow.dto.CategoriaResponse;
import com.finflow.finflow.model.Categoria;
import com.finflow.finflow.model.TipoCategoria;
import com.finflow.finflow.repository.CategoriaRepository;
import com.finflow.finflow.mapper.CategoriaMapper;

/**
 * RF05 – Gerenciamento de Categorias
 * 
 * Esta classe implementa as funcionalidades de gerenciamento de categorias
 * de receitas e despesas no sistema.
 * 
 * Funcionalidades:
 * - Criar categoria
 * - Listar categorias
 * - Buscar categoria por ID
 * - Atualizar categoria
 * - Deletar categoria
 * 
 * Regras:
 * - A categoria deve possuir um nome
 * - O tipo deve ser válido (RECEITA ou DESPESA)
 */

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }
    
     /**
     * RF05 – Criar categoria
     */
    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = CategoriaMapper.toEntity(request);
        return CategoriaMapper.toResponse(repository.save(categoria));
    }

     /**
     * RF05 – Listar categorias
     */
    public List<CategoriaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .collect(Collectors.toList());
    }
     
     /**
     * RF05 – Buscar categoria por ID
     */
    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow();
        return CategoriaMapper.toResponse(categoria);
    }

     /**
     * RF05 – Atualizar categoria
     */
    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {
        Categoria categoria = repository.findById(id).orElseThrow();

        categoria.setNome(request.getNome());

        
        categoria.setTipo(
            TipoCategoria.valueOf(request.getTipo().toUpperCase())
        );

        return CategoriaMapper.toResponse(repository.save(categoria));
    }
    
     /**
     * RF05 – Deletar categoria
     */
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
