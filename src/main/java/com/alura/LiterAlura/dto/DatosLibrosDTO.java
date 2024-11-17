package com.alura.LiterAlura.dto;

import com.alura.LiterAlura.model.Lenguajes;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibrosDTO(
        @JsonAlias("title") String titulo ,
        @JsonAlias("authors") List<DatosAutorDTO> autor,
        @JsonAlias("languages") List<Lenguajes> lenguajes,
        @JsonAlias("id") Integer libroID,
        @JsonAlias("download_count") Integer cantDescarga
) {
}
