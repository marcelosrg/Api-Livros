package com.marcelosg.libraryapi.model;

import com.marcelosg.libraryapi.model.enuns.GeneroLivro;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity()

@Getter
@Setter
@ToString(exclude = "autor")
@Table(name="livro")
@EntityListeners(AuditingEntityListener.class) //isso faz com que fica escutando toda vez que tiver alteracao nessa entidade pra atualizar a data de atualizacao ou quando cadastrar criar a data de cadastro
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

    @CreatedDate
    @Column(name= "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name= "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name= "id_user")
    private UUID idUsuario;

    @JoinColumn(name="id_autor")
    @ManyToOne(
            fetch = FetchType.LAZY //retorna somente os dados do livro

            //fetch = FetchType.EAGER // retorna os dados do livro e do autor
    )
    private Autor autor;

}
