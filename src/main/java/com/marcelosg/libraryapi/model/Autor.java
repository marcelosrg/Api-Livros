package com.marcelosg.libraryapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)

    @Column(name= "id")
    private UUID id;

    @Column(name= "nome", nullable = false, length = 100)

    private String  nome;

    @Column(name= "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name= "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

   @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

}
