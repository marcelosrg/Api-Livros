package com.marcelosg.libraryapi.repository;

import com.fasterxml.jackson.databind.node.DecimalNode;
import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.Livro;
import com.marcelosg.libraryapi.model.enuns.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest(){
        Livro livro = new Livro();
        livro.setTitulo("Entendendo Algoritmos");
        livro.setIsbn("123456789");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(1990,1,2));
        livro.setPreco(BigDecimal.valueOf(54));

        Autor autor = autorRepository.findById(UUID.fromString("6f050300-bd19-4992-890f-288972946af6")).orElse(null);
        livro.setAutor(autor);


        livroRepository.save(livro);


    }
}