package com.marcelosg.libraryapi.controller;

import com.marcelosg.libraryapi.model.dtos.AutorDto;
import com.marcelosg.libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/autores")
public class AutorController {


    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("criar")
    public ResponseEntity<Void> criarAutor(@RequestBody AutorDto autor){

       var autorEntity = autor.mappingAutorEntity();
       autorService.save(autorEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
