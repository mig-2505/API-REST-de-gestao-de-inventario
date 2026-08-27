package dev.miguel.inventarioapi.repository;

import dev.miguel.inventarioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Diz ao Spring que esta interface fala com o banco de dados
public interface ProdutoRepository extends JpaRepository<Produto, Long> { } //long por conta que é o tipo da nossa chave primaria