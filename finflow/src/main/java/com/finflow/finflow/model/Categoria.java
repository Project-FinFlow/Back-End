package com.finflow.finflow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}