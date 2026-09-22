# API REST de Gestão de Inventário

Projeto desenvolvido como portfólio para demonstrar habilidades em **Java 17+ e Spring Boot 3**.

Esta API simula o gerenciamento de estoque de um E-commerce, permitindo cadastrar produtos, consultar inventário e registrar vendas com baixa automática de estoque. O projeto conta com validações rígidas de entrada, transações seguras, proteção de dados via DTOs e tratamento global de exceções.

## 🛠️ Tecnologias e Padrões Utilizados
* **Linguagem:** Java 17+ (Uso de *Records* para DTOs imutáveis)
* **Framework:** Spring Boot 3
* **Banco de Dados:** Oracle Database (XE)
* **Persistência:** Spring Data JPA / Hibernate
* **Validação:** Jakarta Bean Validation

## Endpoints da API

| Método | Endpoint | Descrição | Status Sucesso |
| :--- | :--- | :--- | :--- |
| **POST** | `/produtos` | Cadastra um novo produto no estoque | `201 Created` |
| **GET** | `/produtos` | Lista todos os produtos cadastrados | `200 OK` |
| **POST** | `/produtos/{id}/vender?quantidade=X` | Registra venda e realiza baixa automática no estoque | `200 OK` |

## Como executar o projeto
1. Certifique-se de ter o **Oracle XE** rodando na porta `1521`.
2. Configure a variável de ambiente `DB_PASSWORD` na sua IDE com a senha do seu banco.
3. Execute a classe `InventoryApiApplication`.
4. Utilize o **Postman** ou **Insomnia** para disparar requisições para `http://localhost:8080/produtos`.