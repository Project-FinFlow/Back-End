package com.finflow.finflow.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.finflow.finflow.model.Categoria;
import com.finflow.finflow.model.Despesa;
import com.finflow.finflow.model.LogSistema;
import com.finflow.finflow.model.Meta;
import com.finflow.finflow.model.Receita;
import com.finflow.finflow.model.TipoCategoria;
import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.CategoriaRepository;
import com.finflow.finflow.repository.DespesaRepository;
import com.finflow.finflow.repository.LogSistemaRepository;
import com.finflow.finflow.repository.MetaRepository;
import com.finflow.finflow.repository.ReceitaRepository;
import com.finflow.finflow.repository.UsuarioRepository;

@Configuration
public class SampleDataInitializer {

    @Bean
    CommandLineRunner seedSampleData(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            DespesaRepository despesaRepository,
            ReceitaRepository receitaRepository,
            MetaRepository metaRepository,
            LogSistemaRepository logSistemaRepository) {
        return args -> {
            Map<String, Usuario> usuarios = garantirUsuarios(usuarioRepository);
            Map<String, Categoria> categorias = garantirCategorias(categoriaRepository, usuarios);

            garantirDespesas(despesaRepository, usuarios, categorias);
            garantirReceitas(receitaRepository, usuarios, categorias);
            garantirMetas(metaRepository, usuarios);
            garantirLogs(logSistemaRepository, usuarios, categorias);
        };
    }

    private Map<String, Usuario> garantirUsuarios(UsuarioRepository repository) {
        Map<String, Usuario> usuariosPorEmail = new LinkedHashMap<>();

        for (Usuario usuario : repository.findAll()) {
            usuariosPorEmail.put(usuario.getEmail(), usuario);
        }

        criarUsuarioSeAusente(
                repository,
                usuariosPorEmail,
                "gui@email.com",
                "Guilherme",
                "123456");
        criarUsuarioSeAusente(
                repository,
                usuariosPorEmail,
                "HopeX@email.com",
                "Esperanca",
                "12345");
        criarUsuarioSeAusente(
                repository,
                usuariosPorEmail,
                "maria@email.com",
                "Maria",
                "abc123");

        return usuariosPorEmail;
    }

    private void criarUsuarioSeAusente(
            UsuarioRepository repository,
            Map<String, Usuario> usuariosPorEmail,
            String email,
            String nome,
            String senha) {
        if (usuariosPorEmail.containsKey(email)) {
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        Usuario salvo = repository.save(usuario);
        usuariosPorEmail.put(email, salvo);
    }

    private Map<String, Categoria> garantirCategorias(
            CategoriaRepository repository,
            Map<String, Usuario> usuarios) {
        Map<String, Categoria> categoriasPorNome = new LinkedHashMap<>();

        for (Categoria categoria : repository.findAll()) {
            categoriasPorNome.put(categoria.getNome(), categoria);
        }

        criarCategoriaSeAusente(
                repository,
                categoriasPorNome,
                "Alimentacao",
                TipoCategoria.DESPESA,
                usuarios.get("gui@email.com"));
        criarCategoriaSeAusente(
                repository,
                categoriasPorNome,
                "Transporte",
                TipoCategoria.DESPESA,
                usuarios.get("gui@email.com"));
        criarCategoriaSeAusente(
                repository,
                categoriasPorNome,
                "Moradia",
                TipoCategoria.DESPESA,
                usuarios.get("HopeX@email.com"));
        criarCategoriaSeAusente(
                repository,
                categoriasPorNome,
                "Salario",
                TipoCategoria.RECEITA,
                usuarios.get("gui@email.com"));
        criarCategoriaSeAusente(
                repository,
                categoriasPorNome,
                "Freelance",
                TipoCategoria.RECEITA,
                usuarios.get("maria@email.com"));

        return categoriasPorNome;
    }

    private void criarCategoriaSeAusente(
            CategoriaRepository repository,
            Map<String, Categoria> categoriasPorNome,
            String nome,
            TipoCategoria tipo,
            Usuario usuario) {
        if (categoriasPorNome.containsKey(nome)) {
            return;
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setTipo(tipo);
        categoria.setUsuario(usuario);

        Categoria salva = repository.save(categoria);
        categoriasPorNome.put(nome, salva);
    }

    private void garantirDespesas(
            DespesaRepository repository,
            Map<String, Usuario> usuarios,
            Map<String, Categoria> categorias) {
        Map<String, Despesa> despesasPorDescricao = new LinkedHashMap<>();

        for (Despesa despesa : repository.findAll()) {
            despesasPorDescricao.put(despesa.getDescricao(), despesa);
        }

        criarDespesaSeAusente(
                repository,
                despesasPorDescricao,
                "Compra no mercado",
                "50.00",
                LocalDate.of(2026, 4, 24),
                usuarios.get("gui@email.com"),
                categorias.get("Alimentacao"));
        criarDespesaSeAusente(
                repository,
                despesasPorDescricao,
                "Gasolina do carro",
                "120.00",
                LocalDate.of(2026, 4, 25),
                usuarios.get("gui@email.com"),
                categorias.get("Transporte"));
        criarDespesaSeAusente(
                repository,
                despesasPorDescricao,
                "Conta de luz",
                "230.00",
                LocalDate.of(2026, 4, 26),
                usuarios.get("HopeX@email.com"),
                categorias.get("Moradia"));
    }

    private void criarDespesaSeAusente(
            DespesaRepository repository,
            Map<String, Despesa> despesasPorDescricao,
            String descricao,
            String valor,
            LocalDate data,
            Usuario usuario,
            Categoria categoria) {
        if (despesasPorDescricao.containsKey(descricao)) {
            return;
        }

        Despesa despesa = new Despesa();
        despesa.setDescricao(descricao);
        despesa.setValor(new BigDecimal(valor));
        despesa.setData(data);
        despesa.setUsuario(usuario);
        despesa.setCategoria(categoria);

        Despesa salva = repository.save(despesa);
        despesasPorDescricao.put(descricao, salva);
    }

    private void garantirReceitas(
            ReceitaRepository repository,
            Map<String, Usuario> usuarios,
            Map<String, Categoria> categorias) {
        Map<String, Receita> receitasPorDescricao = new LinkedHashMap<>();

        for (Receita receita : repository.findAll()) {
            receitasPorDescricao.put(receita.getDescricao(), receita);
        }

        criarReceitaSeAusente(
                repository,
                receitasPorDescricao,
                "Salario",
                "5000.00",
                LocalDate.of(2026, 4, 24),
                usuarios.get("gui@email.com"),
                categorias.get("Salario"));
        criarReceitaSeAusente(
                repository,
                receitasPorDescricao,
                "Projeto freelance",
                "1800.00",
                LocalDate.of(2026, 4, 26),
                usuarios.get("HopeX@email.com"),
                categorias.get("Freelance"));
        criarReceitaSeAusente(
                repository,
                receitasPorDescricao,
                "Venda online",
                "650.00",
                LocalDate.of(2026, 4, 27),
                usuarios.get("maria@email.com"),
                categorias.get("Freelance"));
    }

    private void criarReceitaSeAusente(
            ReceitaRepository repository,
            Map<String, Receita> receitasPorDescricao,
            String descricao,
            String valor,
            LocalDate data,
            Usuario usuario,
            Categoria categoria) {
        if (receitasPorDescricao.containsKey(descricao)) {
            return;
        }

        Receita receita = new Receita();
        receita.setDescricao(descricao);
        receita.setValor(new BigDecimal(valor));
        receita.setData(data);
        receita.setUsuario(usuario);
        receita.setCategoria(categoria);

        Receita salva = repository.save(receita);
        receitasPorDescricao.put(descricao, salva);
    }

    private void garantirMetas(
            MetaRepository repository,
            Map<String, Usuario> usuarios) {
        Map<String, Meta> metasPorDescricao = new LinkedHashMap<>();

        for (Meta meta : repository.findAll()) {
            metasPorDescricao.put(meta.getDescricao(), meta);
        }

        criarMetaSeAusente(
                repository,
                metasPorDescricao,
                "Guardar dinheiro",
                1000.00,
                100.00,
                usuarios.get("gui@email.com"));
        criarMetaSeAusente(
                repository,
                metasPorDescricao,
                "Viagem de fim de ano",
                3500.00,
                900.00,
                usuarios.get("HopeX@email.com"));
        criarMetaSeAusente(
                repository,
                metasPorDescricao,
                "Reserva de emergencia",
                5000.00,
                1200.00,
                usuarios.get("maria@email.com"));
    }

    private void criarMetaSeAusente(
            MetaRepository repository,
            Map<String, Meta> metasPorDescricao,
            String descricao,
            Double valorObjetivo,
            Double valorAtual,
            Usuario usuario) {
        if (metasPorDescricao.containsKey(descricao)) {
            return;
        }

        Meta meta = new Meta();
        meta.setDescricao(descricao);
        meta.setValorObjetivo(valorObjetivo);
        meta.setValorAtual(valorAtual);
        meta.setUsuario(usuario);

        Meta salva = repository.save(meta);
        metasPorDescricao.put(descricao, salva);
    }

    private void garantirLogs(
            LogSistemaRepository repository,
            Map<String, Usuario> usuarios,
            Map<String, Categoria> categorias) {
        Map<String, LogSistema> logsPorDescricao = new LinkedHashMap<>();

        for (LogSistema log : repository.findAll()) {
            logsPorDescricao.put(log.getDescricao(), log);
        }

        criarLogSeAusente(
                repository,
                logsPorDescricao,
                "CREATE",
                "usuario",
                usuarios.get("gui@email.com").getId(),
                "Usuarios de exemplo cadastrados",
                usuarios.get("gui@email.com"),
                LocalDateTime.of(2026, 4, 24, 9, 0));
        criarLogSeAusente(
                repository,
                logsPorDescricao,
                "CREATE",
                "categoria",
                categorias.get("Alimentacao").getId(),
                "Categorias iniciais de exemplo criadas",
                usuarios.get("gui@email.com"),
                LocalDateTime.of(2026, 4, 24, 9, 10));
        criarLogSeAusente(
                repository,
                logsPorDescricao,
                "CREATE",
                "movimentacao",
                1L,
                "Despesas, receitas e metas de exemplo carregadas",
                usuarios.get("HopeX@email.com"),
                LocalDateTime.of(2026, 4, 24, 9, 20));
    }

    private void criarLogSeAusente(
            LogSistemaRepository repository,
            Map<String, LogSistema> logsPorDescricao,
            String acao,
            String tabela,
            Long registroId,
            String descricao,
            Usuario usuario,
            LocalDateTime dataHora) {
        if (logsPorDescricao.containsKey(descricao)) {
            return;
        }

        LogSistema log = new LogSistema();
        log.setAcao(acao);
        log.setTabela(tabela);
        log.setRegistroId(registroId);
        log.setDescricao(descricao);
        log.setUsuario(usuario);
        log.setDataHora(dataHora);

        LogSistema salvo = repository.save(log);
        logsPorDescricao.put(descricao, salvo);
    }
}
