package com.marcelosg.libraryapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "livros")
@Table(name="autor")
@EntityListeners(AuditingEntityListener.class)//isso faz com que fica escutando toda vez que tiver alteracao nessa entidade pra atualizar a data de atualizacao ou quando cadastrar criar a data de cadastro
public class Autor {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)

    @Column(name= "id")
    private UUID id;

    @Column(name= "nome", nullable = false, length = 100)

    private String  nome;

    @Column(name= "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name= "nacionalidade")
    private String nacionalidade;

    @CreatedDate
    @Column(name= "data_cadastro")
    private LocalDateTime dataCadastro;


    @LastModifiedDate
    @Column(name= "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name= "id_user")
    private UUID idUsuario;

   @OneToMany(mappedBy = "autor", cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Livro> livros;


}
