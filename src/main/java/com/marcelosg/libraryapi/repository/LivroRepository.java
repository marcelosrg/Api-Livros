package com.marcelosg.libraryapi.repository;

import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor(Autor autor);
}
