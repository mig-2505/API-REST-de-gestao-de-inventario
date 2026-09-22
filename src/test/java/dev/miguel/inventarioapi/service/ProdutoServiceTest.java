package dev.miguel.inventarioapi.service;

import dev.miguel.inventarioapi.dto.ProdutoRequestDTO;
import dev.miguel.inventarioapi.dto.ProdutoResponseDTO;
import dev.miguel.inventarioapi.model.Produto;
import dev.miguel.inventarioapi.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito nesta classe
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository; // O banco de dados falso

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    @DisplayName("Deve cadastrar um produto com sucesso")
    void deveCadastrarProdutoComSucesso() {
        // ARRANGE
        ProdutoRequestDTO requestDTO = new ProdutoRequestDTO("Notebook", "NOTE-01", new BigDecimal("5000.00"), 10);

        Produto produtoSalvoNoBanco = new Produto();
        produtoSalvoNoBanco.setId(1L);
        produtoSalvoNoBanco.setNome("Notebook");
        produtoSalvoNoBanco.setSku("NOTE-01");
        produtoSalvoNoBanco.setPreco(BigDecimal.valueOf(5000.0));
        produtoSalvoNoBanco.setQuantidadeEstoque(10);

        // Ensinando o Mock
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvoNoBanco);

        // ACT
        ProdutoResponseDTO response = produtoService.cadastrarProduto(requestDTO);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Notebook", response.nome());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve estourar erro ao tentar vender quantidade maior que o estoque")
    void deveLancarExcecaoAoVenderSemEstoque() {
        // ARRANGE
        Produto produtoNoBanco = new Produto();
        produtoNoBanco.setId(1L);
        produtoNoBanco.setQuantidadeEstoque(5); // Temos apenas 5 unidades no banco falso

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoNoBanco));

        // ACT & ASSERT
        // Capturamos a explosão do erro ao tentar vender 10 unidades
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoService.registrarVenda(1L, 10);
        });

        // Verificamos se a mensagem de erro é exatamente a que escrevemos no Service
        assertEquals("Estoque insuficiente! Estoque atual: 5", exception.getMessage());

        // Garantir que o banco NUNCA foi chamado para salvar essa venda inválida
        verify(produtoRepository, never()).save(any(Produto.class));
    }
}