package com.marcelosg.libraryapi.validator;

import com.marcelosg.libraryapi.exception.RegistroDuplicadoExeption;
import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private final AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor){
        if(existeAutor(autor)){
            throw new RegistroDuplicadoExeption("Autor já cadastrado");
        }
    }

    private boolean existeAutor(Autor autor){

        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );

        if(autor.getId() == null){
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
