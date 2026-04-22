📌 Requisitos Funcionais do Sistema – FinFlow

RF01 – Cadastro de Usuário

- Implementação: UsuarioService
- Descrição: Permite cadastrar usuários no sistema

---

RF02 – Login e Autenticação

- Implementação: Parcialmente feito em UsuarioService
- Descrição: Permite autenticação do usuário

---

RF03 – Registro de Receitas

- Implementação: ReceitaService
- Métodos:
  - criar()
  - listar()
  - buscarPorId()
  - atualizar()
  - deletar()

---

RF04 – Registro de Despesas

- Implementação: DespesaService

---

RF05 – Gerenciamento de Categorias

- Implementação: CategoriaService

---

RF06 – Visualização de Saldo

- Implementação: (parcial ou não identificado)
- Descrição: Cálculo de saldo com base em receitas e despesas

---

RF07 – Relatórios Financeiros

- Implementação: Não implementado (por enquanto)
- Descrição: Geração de relatórios mensais/semanais

---

RF08 – Metas Financeiras

- Implementação: MetaService

---

RF09 – Exportação de Dados

- Implementação: Não implementado (por enquanto)
- Descrição: Exportação em PDF/CSV
