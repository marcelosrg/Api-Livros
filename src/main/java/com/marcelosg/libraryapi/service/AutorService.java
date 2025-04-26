package com.marcelosg.libraryapi.service;
import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.repository.AutorRepository;
import com.marcelosg.libraryapi.validator.AutorValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorValidator autorValidator;

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
    }


    public  Autor save(Autor autor){
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){

        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisarAutores(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            autorRepository.findByNomeAndNacionalidade(nome,nacionalidade);
        }

        if(nome != null){
            return  autorRepository.findByNome(nome);
        }

        if(nacionalidade != null){
            return  autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    public void atualizarAutor(UUID id, Autor autor){

        autorValidator.validar(autor);
        Optional<Autor> autorOptional = autorRepository.findById(id);
        if(autorOptional == null && id != autor.getId()){
            throw new IllegalArgumentException("O autor não é valido");
        }
        autorRepository.save(autor);
    }
}
