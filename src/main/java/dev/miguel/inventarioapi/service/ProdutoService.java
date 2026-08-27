package dev.miguel.inventarioapi.service;

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
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // VENDER
    @Transactional // Garante que, se der erro no meio, o banco desfaz a operação automaticamente
    public Produto registrarVenda(Long produtoId, Integer quantidadeVendida) {

        // 1. Buscamos o produto no banco. Se não achar, "estouramos" um erro.
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoId));

        // 2. Verificamos se há estoque suficiente
        if (produto.getQuantidadeEstoque() < quantidadeVendida) {
            throw new IllegalArgumentException("Estoque insuficiente! Estoque atual: " + produto.getQuantidadeEstoque());
        }

        // 3. Subtraímos o estoque
        Integer novoEstoque = produto.getQuantidadeEstoque() - quantidadeVendida;
        produto.setQuantidadeEstoque(novoEstoque);

        // 4. Salvamos a atualização no banco
        return produtoRepository.save(produto);
    }
}