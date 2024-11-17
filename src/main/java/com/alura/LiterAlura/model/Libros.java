package com.alura.LiterAlura.model;

import com.alura.LiterAlura.dto.DatosLibrosDTO;
import com.alura.LiterAlura.dto.ResultadosLibrosDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    private Integer libroId;

    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Lenguajes lenguaje;

    private Integer cantDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libros(Integer libroId, String titulo, Lenguajes lenguaje, Autor autor) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.lenguaje = lenguaje;
        this.autor = autor;
    }

    public Libros(DatosLibrosDTO dto) {
        if (dto.autor().isEmpty()){
            System.out.println("Datos vacios");
        }else{
            this.libroId = dto.libroID();
            this.titulo = dto.titulo();
            this.lenguaje = dto.lenguajes().get(0);
            this.autor = new Autor(dto.autor().get(0));
            this.cantDescargas = dto.cantDescarga();
        }
    }

    public Libros() {

    }

    public Integer getCantDescargas() {
        return cantDescargas;
    }

    public void setCantDescargas(Integer cantDescargas) {
        this.cantDescargas = cantDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Lenguajes getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguajes lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Override
    public String toString() {
        return "--------------- LIBRO ---------------\n" +
                "Titulo: " + this.titulo + "\n" +
                "Autor: " + this.autor.getNombre() + "\n" +
                "Idioma: " + this.lenguaje +  "\n"+
                "Numero de Descargas: " + this.cantDescargas  +  "\n" +
                "------------------------------------";
    }

}
