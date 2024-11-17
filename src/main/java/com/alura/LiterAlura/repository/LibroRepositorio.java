package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.Lenguajes;
import com.alura.LiterAlura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libros, Long> {

    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);

    @Query("select  l from  Libros  l where l.lenguaje= :idioma")
    List<Libros> findByidioma(Lenguajes idioma);

}
