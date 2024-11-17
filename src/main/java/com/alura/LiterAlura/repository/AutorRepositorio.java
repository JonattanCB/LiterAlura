package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepositorio extends JpaRepository<Autor, Long>{

    @Query("select a from  Autor  a WHERE :anio between  a.anioNacimiento and a.anioFallecimiento")
    List<Autor> aniosVidaAutor(Integer anio);

}
