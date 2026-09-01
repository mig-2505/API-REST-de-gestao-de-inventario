package dev.miguel.inventarioapi.dto;

import dev.miguel.inventarioapi.model.Produto;
import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String sku,
        BigDecimal preco,
        Integer quantidadeEstoque
) {
    // Esse construtor extra ajuda a transformar nossa Entidade em DTO
    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getSku(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }
}