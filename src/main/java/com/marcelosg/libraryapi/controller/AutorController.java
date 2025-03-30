package com.marcelosg.libraryapi.controller;

import com.marcelosg.libraryapi.model.dtos.AutorDto;
import com.marcelosg.libraryapi.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/autores")
public class AutorController {


    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping("criar")
    public ResponseEntity criarAutor(@RequestBody AutorDto autor){
        return  ResponseEntity.ok(HttpStatus.CREATED);
    }
}
