package com.marcelosg.libraryapi.model;

import com.marcelosg.libraryapi.model.enuns.GeneroLivro;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity()

@Getter
@Setter
@ToString(exclude = "autor")
@Table(name="livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="isbn", nullable=false, length=20)
    private String isbn;

    @Column(name="titulo", nullable=false, length=250)
    private String titulo;

    @Column(name="data_publicacao", nullable=false)
    private LocalDate dataPublicacao;

    @Column(name="genero", nullable=false)
    @Enumerated(EnumType.STRING)
    private GeneroLivro genero;

    @Column(name="preco",precision=18,scale=2)
    private BigDecimal preco;

    @JoinColumn(name="id_autor")
    @ManyToOne(
            fetch = FetchType.LAZY //retorna somente os dados do livro

            //fetch = FetchType.EAGER // retorna os dados do livro e do autor
    )
    private Autor autor;

}
