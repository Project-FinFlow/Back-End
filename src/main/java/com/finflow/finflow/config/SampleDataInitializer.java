package com.finflow.finflow.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
            Usuario guilherme = garantirUsuario(
                    usuarioRepository,
                    "Guilherme",
                    "gui@email.com",
                    "123456");
            Usuario esperanca = garantirUsuario(
                    usuarioRepository,
                    "Esperanca",
                    "HopeX@email.com",
                    "12345");
            Usuario maria = garantirUsuario(
                    usuarioRepository,
                    "Maria",
                    "maria@email.com",
                    "abc123");

            Categoria alimentacao = garantirCategoria(
                    categoriaRepository,
                    "Alimentacao",
                    TipoCategoria.DESPESA,
                    guilherme);
            Categoria transporte = garantirCategoria(
                    categoriaRepository,
                    "Transporte",
                    TipoCategoria.DESPESA,
                    guilherme);
            Categoria moradia = garantirCategoria(
                    categoriaRepository,
                    "Moradia",
                    TipoCategoria.DESPESA,
                    esperanca);
            Categoria salario = garantirCategoria(
                    categoriaRepository,
                    "Salario",
                    TipoCategoria.RECEITA,
                    guilherme);
            Categoria freelance = garantirCategoria(
                    categoriaRepository,
                    "Freelance",
                    TipoCategoria.RECEITA,
                    maria);

            garantirDespesa(
                    despesaRepository,
                    "Compra no mercado",
                    "50.00",
                    LocalDate.of(2026, 4, 24),
                    guilherme,
                    alimentacao);
            garantirDespesa(
                    despesaRepository,
                    "Gasolina do carro",
                    "120.00",
                    LocalDate.of(2026, 4, 25),
                    guilherme,
                    transporte);
            garantirDespesa(
                    despesaRepository,
                    "Conta de luz",
                    "230.00",
                    LocalDate.of(2026, 4, 26),
                    esperanca,
                    moradia);

            garantirReceita(
                    receitaRepository,
                    "Salario",
                    "5000.00",
                    LocalDate.of(2026, 4, 24),
                    guilherme,
                    salario);
            garantirReceita(
                    receitaRepository,
                    "Projeto freelance",
                    "1800.00",
                    LocalDate.of(2026, 4, 26),
                    esperanca,
                    freelance);
            garantirReceita(
                    receitaRepository,
                    "Venda online",
                    "650.00",
                    LocalDate.of(2026, 4, 27),
                    maria,
                    freelance);

            garantirMeta(
                    metaRepository,
                    "Guardar dinheiro",
                    1000.00,
                    100.00,
                    guilherme);
            garantirMeta(
                    metaRepository,
                    "Viagem de fim de ano",
                    3500.00,
                    900.00,
                    esperanca);
            garantirMeta(
                    metaRepository,
                    "Reserva de emergencia",
                    5000.00,
                    1200.00,
                    maria);

            garantirLog(
                    logSistemaRepository,
                    "CREATE",
                    "usuario",
                    guilherme.getId(),
                    "Usuarios de exemplo cadastrados",
                    guilherme,
                    LocalDateTime.of(2026, 4, 24, 9, 0));
            garantirLog(
                    logSistemaRepository,
                    "CREATE",
                    "categoria",
                    alimentacao.getId(),
                    "Categorias iniciais de exemplo criadas",
                    guilherme,
                    LocalDateTime.of(2026, 4, 24, 9, 10));
            garantirLog(
                    logSistemaRepository,
                    "CREATE",
                    "movimentacao",
                    1L,
                    "Despesas, receitas e metas de exemplo carregadas",
                    esperanca,
                    LocalDateTime.of(2026, 4, 24, 9, 20));
        };
    }

    private Usuario garantirUsuario(
            UsuarioRepository repository,
            String nome,
            String email,
            String senha) {
        Usuario existente = repository.findByEmail(email);

        if (existente != null) {
            return existente;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return repository.save(usuario);
    }

    private Categoria garantirCategoria(
            CategoriaRepository repository,
            String nome,
            TipoCategoria tipo,
            Usuario usuario) {
        Categoria existente = repository.findByNome(nome);

        if (existente != null) {
            return existente;
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setTipo(tipo);
        categoria.setUsuario(usuario);

        return repository.save(categoria);
    }

    private void garantirDespesa(
            DespesaRepository repository,
            String descricao,
            String valor,
            LocalDate data,
            Usuario usuario,
            Categoria categoria) {
        if (repository.existsByDescricao(descricao)) {
            return;
        }

        Despesa despesa = new Despesa();
        despesa.setDescricao(descricao);
        despesa.setValor(new BigDecimal(valor));
        despesa.setData(data);
        despesa.setUsuario(usuario);
        despesa.setCategoria(categoria);

        repository.save(despesa);
    }

    private void garantirReceita(
            ReceitaRepository repository,
            String descricao,
            String valor,
            LocalDate data,
            Usuario usuario,
            Categoria categoria) {
        if (repository.existsByDescricao(descricao)) {
            return;
        }

        Receita receita = new Receita();
        receita.setDescricao(descricao);
        receita.setValor(new BigDecimal(valor));
        receita.setData(data);
        receita.setUsuario(usuario);
        receita.setCategoria(categoria);

        repository.save(receita);
    }

    private void garantirMeta(
            MetaRepository repository,
            String descricao,
            Double valorObjetivo,
            Double valorAtual,
            Usuario usuario) {
        if (repository.existsByDescricao(descricao)) {
            return;
        }

        Meta meta = new Meta();
        meta.setDescricao(descricao);
        meta.setValorObjetivo(valorObjetivo);
        meta.setValorAtual(valorAtual);
        meta.setUsuario(usuario);

        repository.save(meta);
    }

    private void garantirLog(
            LogSistemaRepository repository,
            String acao,
            String tabela,
            Long registroId,
            String descricao,
            Usuario usuario,
            LocalDateTime dataHora) {
        if (repository.existsByDescricao(descricao)) {
            return;
        }

        LogSistema log = new LogSistema();
        log.setAcao(acao);
        log.setTabela(tabela);
        log.setRegistroId(registroId);
        log.setDescricao(descricao);
        log.setUsuario(usuario);
        log.setDataHora(dataHora);

        repository.save(log);
    }
}
