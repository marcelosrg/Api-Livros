package com.marcelosg.libraryapi.repository;

import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.Livro;
import com.marcelosg.libraryapi.model.enuns.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    @Autowired
    public AutorRepositoryTest(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @Test
    public void saveAutorTest() {

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 4, 24));
        var autorSave = autorRepository.save(autor);

        System.out.println("Autor salvo:" + autorSave);
    }


    @Test
    public void atualizarAutorTest(){
        var id = UUID.fromString("b28c5257-c689-4485-9aba-0e7c600e4e9d");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncotrado = possivelAutor.get();

            System.out.println("Dados do Autor:");
            System.out.println(autorEncotrado);

            autorEncotrado.setNome("Ana");

            autorRepository.save(autorEncotrado);
        }
    }

    @Test
    public void listarAutoresTest(){

      List<Autor> autores = autorRepository.findAll();

      autores.forEach(System.out::println);
    }


    @Test
    public void contarAutoresTest(){
        System.out.println("Contando autores: " + autorRepository.count());
    }

    @Test
    public void deletarAutorTest(){
        var id = UUID.fromString("b28c5257-c689-4485-9aba-0e7c600e4e9d");

        Optional<Autor> autor = autorRepository.findById(id);

        if(autor.isPresent()){
            autorRepository.deleteById(id);
        }
    }

    @Test
    void salvarAutorComLivros(){
        Autor autor = new Autor();
        autor.setNome("Joe");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1941, 4, 14));


        Livro livro = new Livro();
        livro.setTitulo("Loucura esse Java");
        livro.setIsbn("123456789");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2002,1,21));
        livro.setPreco(BigDecimal.valueOf(54));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setTitulo("Loucura esse Java qqisso");
        livro2.setIsbn("123456789");
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setDataPublicacao(LocalDate.of(2002,1,21));
        livro2.setPreco(BigDecimal.valueOf(54));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }
}
