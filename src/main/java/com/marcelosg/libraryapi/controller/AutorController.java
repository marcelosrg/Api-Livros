package com.marcelosg.libraryapi.controller;

import com.marcelosg.libraryapi.exception.RegistroDuplicadoExeption;
import com.marcelosg.libraryapi.model.Autor;
import com.marcelosg.libraryapi.model.dtos.AutorDto;
import com.marcelosg.libraryapi.model.dtos.ErroResposta;
import com.marcelosg.libraryapi.service.AutorService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/autores")
public class AutorController {


    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("criar")
    public ResponseEntity<Object> criarAutor(@RequestBody AutorDto autor){

        try{

           var autorEntity = autor.mappingAutorEntity();
           autorService.save(autorEntity);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntity.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }catch(RegistroDuplicadoExeption e){

            var errorDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<AutorDto> obterDetlhes(@PathVariable("id") UUID id) {
        Optional<Autor> autorOptional = autorService.obterPorId(id);

        if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDto dto = new AutorDto(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());

            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id")UUID id) {
        Optional<Autor> autorOptional = autorService.obterPorId(id);
        if(autorOptional.isEmpty()){

            return ResponseEntity.notFound().build();
        }
        autorService.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<List<AutorDto>> pesquisarAutores(@RequestParam(value="nome", required = false) String nome, @RequestParam(value="nacionalidade",required = false) String nacionalidade){

        List<Autor> resultado = autorService.pesquisarAutores(nome, nacionalidade);
        List<AutorDto> autores = resultado.stream().map(autor -> new AutorDto(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade())).collect(Collectors.toList());
        return ResponseEntity.ok(autores);
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> autualizar(@PathVariable("id") UUID id,@RequestBody AutorDto autorDto){

        try{
            Optional<Autor> autorOptional = autorService.obterPorId(id);

            if(autorOptional.isEmpty()){

                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();

            autor.setNome(autorDto.nome());
            autor.setNacionalidade(autorDto.nacionalidade());
            autor.setDataNascimento(autorDto.dataNascimento());

            autorService.save(autor);
            return ResponseEntity.noContent().build();

        }catch(RegistroDuplicadoExeption e){
            var errorDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }
}
