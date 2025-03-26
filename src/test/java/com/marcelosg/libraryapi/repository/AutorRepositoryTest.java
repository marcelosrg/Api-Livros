package com.marcelosg.libraryapi.repository;

import com.marcelosg.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorRepositoryTest(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

   /* @Test
    public void saveAutor() {

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 4, 24));
        var autorSave = autorRepository.save(autor);

        System.out.println("Autor salvo:" + autorSave);
    }
*/

    @Test
    public void atualizarAutor(){
        var id = UUID.fromString("b28c5257-c689-4485-9aba-0e7c600e4e9d");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncotrado = possivelAutor.get();

            System.out.println("Dados do Autor:");
            System.out.println(autorEncotrado);

            autorEncotrado.setNome("Ana");

            autorRepository.save(autorEncotrado);
        }
    }
}
