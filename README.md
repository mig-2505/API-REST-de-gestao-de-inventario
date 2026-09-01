# API REST de Gestão de Inventário

Projeto desenvolvido como portfólio para demonstrar habilidades em **Java, Spring Boot e Boas Práticas de Engenharia de Software**.

Esta é uma API de simulação de E-commerce, onde é possível cadastrar produtos e registrar vendas com baixa automática de estoque, utilizando regras de negócio isoladas e transações seguras.

## Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** Oracle Database 
* **Persistência:** Spring Data JPA / Hibernate

## O que já foi implementado 
- Conexão com banco de dados Oracle.
- Mapeamento Objeto-Relacional (Entidade `Produto`).
- Regra de negócio de validação de estoque na Venda (`@Transactional`).

## Como rodar o projeto localmente
1. Certifique-se de ter o **Oracle XE** rodando na porta `1521`.
2. Configure a variável de ambiente `DB_PASSWORD` na sua IDE com a senha do banco, ou insira a senha diretamente no arquivo `application.yaml` (apenas para testes locais).
3. Execute a classe `InventoryApiApplication`.