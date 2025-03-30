package com.marcelosg.libraryapi.model.dtos;

import com.marcelosg.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDto(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {


    public Autor mappingAutorEntity(){
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setDataNascimento(dataNascimento);
        autor.setNacionalidade(nacionalidade);
        return autor;
    }

}
