package dev.miguel.inventarioapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity // Diz ao Spring: "Esta classe é uma tabela no banco de dados"
@Table(name = "tb_produto") // É boa prática forçar um nome de tabela padronizado
public class Produto {

    @Id // Diz que este campo é a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o ID automaticamente (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 100) // Não pode ser nulo, máximo 100 caracteres
    private String nome;

    @Column(nullable = false, unique = true, length = 50) // O SKU é único, não podem existir dois produtos com o mesmo código
    private String sku;

    @Column(nullable = false)
    private BigDecimal preco; // BigDecimal para dinheiro

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    // CONSTRUTOR VAZIO
    public Produto() {
    }

    // GETTERS E SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}