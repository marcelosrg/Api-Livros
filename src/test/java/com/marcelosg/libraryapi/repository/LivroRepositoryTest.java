package com.marcelosg.libraryapi.repository;

import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.Livro;
import com.marcelosg.libraryapi.model.enuns.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    @Test
    public void salvarComCascadeTest(){
        Livro livro = new Livro();
        livro.setTitulo("Entendendo Algoritmos");
        livro.setIsbn("123456789");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(1990,1,2));
        livro.setPreco(BigDecimal.valueOf(54));

        Autor autor = new Autor();
        autor.setNome("Fernando");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 4, 24));

        autorRepository.save(autor);
        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    public void salvarLivroAutorTest(){
        Livro livro = new Livro();
        livro.setTitulo("Entendendo Algoritmos");
        livro.setIsbn("123456789");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(1990,1,2));
        livro.setPreco(BigDecimal.valueOf(54));

        Autor autor = new Autor();
        autor.setNome("Fernando");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 4, 24));

        //pra salvar dessa forma é preciso usar o cascade


        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    @Transactional //caso eu queira trazer os dados do Autor usando o Lazy
    void buscarLivroTest(){
        UUID id = UUID.fromString("27d995d9-2a98-4235-ab9f-12668b86f995");

        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println( livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println( livro.getAutor().getNome());
    }

    @Test
    void buscarLivroPorAutorTest(){
        UUID idAutor = UUID.fromString("6f050300-bd19-4992-890f-288972946af6");

        var autor = autorRepository.findById(idAutor).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }
    @Test
    void pesquisaPorTituloTest(){

        List <Livro> livro = livroRepository.findByTitulo("Loucura esse Java");
        livro.forEach(System.out::println);
    }
     @Test
    void pesquisaPorTituloEPrecoTest(){

        var preco = BigDecimal.valueOf(54.00);


        List <Livro> livro = livroRepository.findByTituloAndPreco("Entendendo Algoritmos", preco);
        livro.forEach(System.out::println);
    }
}

