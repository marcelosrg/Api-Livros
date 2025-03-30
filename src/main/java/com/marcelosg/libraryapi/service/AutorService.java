package com.marcelosg.libraryapi.service;


import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public  Autor save(Autor autor){

        return autorRepository.save(autor);
    }
}
