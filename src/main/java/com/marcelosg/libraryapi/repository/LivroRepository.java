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


    // quando for usar o JPQL -> devemos usar a letra inicial da entidade que ira ser retornada, no exemplo abaixo a entidade e o Livro ent é l
    //a consulta a baixo e a mesma coisa que SELECT * FROM LIVRO WHERE LIVRO.TITULO = ? AND LIVRO.PRECO = ?
    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo AND l.preco = :preco")
    List<Livro> findByTitleAndPrice(@Param("titulo") String titulo, @Param("preco") BigDecimal preco);

    List<Livro> findByTituloAndPreco(String titulo,  BigDecimal preco);


    //aqui e um exemplo de inner join, fazendo a juncao da tabela livro e da tabela autor, no caso o A no select e a entidade que vai retornar


    @Query("select a from Livro l join l.autor a")
    List<Autor> listaAutoresDosLivros();


    //O Exemplo abaixo mostra como criar uma query grande, assim quebrando a linha usando as 3 aspas duplas
//    @Query("""
//            select a from
//            Livro l join l.autor a
//            """)
    //List<Autor> listaAutoresDosLivros();
}
