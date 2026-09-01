package dev.miguel.inventarioapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// O Record já cria os getters, construtores e toString automaticamente
public record ProdutoRequestDTO(

        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,

        @NotBlank(message = "O SKU é obrigatório")
        String sku,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "A quantidade inicial é obrigatória")
        @Min(value = 0, message = "A quantidade não pode ser negativa")
        Integer quantidadeEstoque
) {
}