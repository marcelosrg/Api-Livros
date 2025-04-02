package com.marcelosg.libraryapi.service;
import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public  Autor save(Autor autor){

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
}
