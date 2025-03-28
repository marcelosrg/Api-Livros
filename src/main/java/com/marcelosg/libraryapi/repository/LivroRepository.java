package com.marcelosg.libraryapi.repository;

import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo AND l.preco = :preco")
    List<Livro> findByTitleAndPrice(@Param("titulo") String titulo, @Param("preco") BigDecimal preco);

    List<Livro> findByTituloAndPreco(String titulo,  BigDecimal preco);
}
