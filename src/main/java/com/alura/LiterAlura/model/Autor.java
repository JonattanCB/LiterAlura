package com.alura.LiterAlura.model;

import com.alura.LiterAlura.dto.DatosAutorDTO;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer anioNacimiento;

    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autor() {}

    public Autor(Long id, String nombre, Integer anioNacimiento, Integer anioFallecimiento, List<Libros> libros) {
        this.id = id;
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
        this.libros = libros;
    }


    public Autor(DatosAutorDTO datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioFallecimiento = datosAutor.anioFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioMuerte) {
        this.anioFallecimiento = anioMuerte;
    }

    @Override
    public String toString() {

        return "Autor: " + this.nombre +"\n" +
                "Fecha de nacimiento:" + this.anioNacimiento +"\n" +
                "Fecha de fallecimiento:" + this.anioFallecimiento +"\n";
    }
}
