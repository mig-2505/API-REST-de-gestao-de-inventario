package dev.miguel.inventarioapi.service;

import dev.miguel.inventarioapi.dto.ProdutoRequestDTO;
import dev.miguel.inventarioapi.dto.ProdutoResponseDTO;
import dev.miguel.inventarioapi.model.Produto;
import dev.miguel.inventarioapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Diz ao Spring: "Esta classe contém as regras de negócio"
public class ProdutoService {

    // Injeção de Dependência: Trazemos o Repositório para dentro do Service
    private final ProdutoRepository produtoRepository;

    // Construtor: Uma prática muito valorizada (melhor que usar @Autowired solto)
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // CADASTRAR
    @Transactional
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        // 1. O Service recebe o (DTO) e precisa transformar na Entidade para o banco entender
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setSku(dto.sku());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        // 2. Salva no banco de dados
        Produto produtoSalvo = produtoRepository.save(produto);

        // 3. Transforma a panela salva de volta em um prato limpo para devolver para a web
        return new ProdutoResponseDTO(produtoSalvo);
    }

    // VENDER
    @Transactional
    public ProdutoResponseDTO registrarVenda(Long produtoId, Integer quantidadeVendida) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoId));

        if (produto.getQuantidadeEstoque() < quantidadeVendida) {
            throw new IllegalArgumentException("Estoque insuficiente! Estoque atual: " + produto.getQuantidadeEstoque());
        }

        Integer novoEstoque = produto.getQuantidadeEstoque() - quantidadeVendida;
        produto.setQuantidadeEstoque(novoEstoque);

        Produto produtoAtualizado = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produtoAtualizado);
    }

    // LISTAR TODOS
    public java.util.List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }
}