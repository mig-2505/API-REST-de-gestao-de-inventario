package dev.miguel.inventarioapi.controller;

import dev.miguel.inventarioapi.dto.ProdutoRequestDTO;
import dev.miguel.inventarioapi.dto.ProdutoResponseDTO;
import dev.miguel.inventarioapi.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos") // Endereço principal
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // ENDPOINT DE CADASTRO (POST)
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.cadastrarProduto(dto);

        // Retornar status 201 (Created) e a URL do novo produto no cabeçalho
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    // ENDPOINT DE LISTAGEM (GET)
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        List<ProdutoResponseDTO> lista = produtoService.listarTodos();
        return ResponseEntity.ok(lista); // Retorna status 200 (OK)
    }

    // ENDPOINT DE VENDA (POST com parâmetro na URL)
    @PostMapping("/{id}/vender")
    public ResponseEntity<ProdutoResponseDTO> vender(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        ProdutoResponseDTO response = produtoService.registrarVenda(id, quantidade);
        return ResponseEntity.ok(response); // Retorna status 200 (OK)
    }
}