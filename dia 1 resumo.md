# 🚀 Resumo Oficial: Dia 1 - A Fundação da API de Inventário

## 🎯 Objetivo Alcançado
Criamos a base do projeto Spring Boot, conectamos com o banco de dados Oracle e implementamos a camada mais crítica do sistema: as regras de negócio de cadastro e venda de produtos, aplicando boas práticas de Engenharia de Software.

## 🛠️ 1. Infraestrutura e Configuração
* **Projeto Base:** Gerado via Spring Initializr com as dependências `Spring Web`, `Spring Data JPA`, `Oracle Driver` e `Validation`.
* **Banco de Dados:** Instalamos e conectamos o **Oracle Database (XE)**.
* **application.yaml:** Configuramos as credenciais do banco e habilitamos o `ddl-auto: update` para o Spring criar as tabelas automaticamente, e `show-sql: true` para monitorar os comandos no console.
* **Segurança:** Discutimos o perigo de comitar senhas (`DB_PASSWORD`) no GitHub e como o mercado resolve isso usando Variáveis de Ambiente.

## 💻 2. O Código Produzido (Mão na Massa)
Criamos a estrutura em camadas (MVC/Arquitetura de N-Camadas) focando nos "Trabalhadores" do backend:

1. **Modelo (`Produto.java`):**
   * Usamos a anotação `@Entity` para transformar a classe em uma tabela (`tb_produto`).
   * Definimos as colunas com `@Id`, `@GeneratedValue` (ID automático) e `@Column`.
   * Utilizamos `BigDecimal` para o preço (padrão absoluto para dinheiro em Java).
2. **Repositório (`ProdutoRepository.java`):**
   * Criamos uma interface com o crachá `@Repository`.
   * Estendemos o `JpaRepository<Produto, Long>`, ganhando comandos de banco (salvar, buscar, deletar) sem escrever SQL.
3. **Serviço (`ProdutoService.java`):**
   * O "Cérebro" da aplicação, marcado com `@Service`.
   * Usamos **Injeção de Dependência via Construtor** (com variável `private final`), uma prática Sênior.
   * Implementamos a regra de **Venda**: validamos estoque e subtraímos o valor, tudo protegido pela anotação `@Transactional` contra falhas.

## 🧠 3. Teoria Dominada (Para brilhar nas entrevistas)
* **JPA vs Hibernate:** JPA é a regra. O Hibernate é a ferramenta real que traduz o Java (Objetos) para o Oracle (Tabelas/SQL).
* **Blindagem contra SQL Injection:** O Hibernate usa *Prepared Statements* (`?` nas queries) para separar a ordem (SQL) do dado do usuário, impedindo ataques hackers.
* **Os "Crachás" do Spring:** Apenas os "trabalhadores" (`@Service`, `@Repository`, `@RestController`) recebem crachás para o Spring gerenciar. Classes de dados são criadas sob demanda.

## 🐙 4. Versionamento e Boas Práticas (Git)
* **.gitignore limpo:** Ignoramos pastas locais (`.idea/`) e anotações (`*.txt`).
* **Conventional Commits:** Adotamos o padrão de mercado (`chore: ...`, `feat: ...`).
* **Documentação:** Criamos a primeira versão do `README.md`.
* **O Próximo Passo (Git Flow):** A partir do Dia 2, usaremos o fluxo corporativo: criar uma `branch`, commitar, e abrir um **Pull Request (PR)**.