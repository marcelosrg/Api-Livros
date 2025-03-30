package com.marcelosg.libraryapi.model.dtos;

import java.time.LocalDate;

public record AutorDto(String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {

}
