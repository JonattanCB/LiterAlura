package com.alura.LiterAlura.principal;

import com.alura.LiterAlura.dto.ResultadosLibrosDTO;
import com.alura.LiterAlura.model.Autor;
import com.alura.LiterAlura.model.Lenguajes;
import com.alura.LiterAlura.model.Libros;
import com.alura.LiterAlura.repository.AutorRepositorio;
import com.alura.LiterAlura.repository.LibroRepositorio;
import com.alura.LiterAlura.service.ConsumoAPI;
import com.alura.LiterAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convercion = new ConvierteDatos();
    private AutorRepositorio autorRepositorio;
    private LibroRepositorio libroRepositorio;

    public Principal(AutorRepositorio autorRepositorio, LibroRepositorio libroRepositorio) {
        this.autorRepositorio = autorRepositorio ;
        this.libroRepositorio = libroRepositorio;
    }

    public  void mostrarMenuPrincipal() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(
                    """
                    ------------------------------------------
                            Jonattan Contreras Baltazar
                                       LiterAlura
                    ------------------------------------------
                    Elija la opción a través de su número:
                    1- buscar libro por título
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma

                    0- Salir
                    """
            );
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                     buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("-".repeat(40));
                    System.out.println("Gracias por usar esta Aplicacion ~ <3");
                    System.out.println("-".repeat(40));
                    break;
                default:
                    System.out.println("Opcion Inválida.");
            }
        }
    }
    private ResultadosLibrosDTO getResultadosBusqueda() {
        System.out.println("Ingrese el nombre del libro: ");
        String nombreLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos("https://gutendex.com//books/?search="+nombreLibro.replace(" ","+"));
        return convercion.convertirDatos(json,ResultadosLibrosDTO.class);
    }

    private void buscarLibroPorTitulo() {
        var datos  = getResultadosBusqueda();
        if (datos.resultado().isEmpty()){
            System.out.println("No se encontro el libro");
        }else{
            Autor autor = new Autor(datos.resultado().get(0).autor().get(0));
            Libros  libros = new Libros(datos.resultado().get(0));
            Optional<Libros> librosList = libroRepositorio.findByTituloContainsIgnoreCase(libros.getTitulo());
            if (!librosList.isPresent()){
                List<Autor> autorList = autorRepositorio.findAll();
                if (!autorList.contains(autor)){
                    autorRepositorio.save(autor);
                }
                libros.setAutor(autor);
                libroRepositorio.save(libros);
            }
            System.out.println(libros);
        }
    }

    private void listarLibrosRegistrados(){
        List<Libros> libros = libroRepositorio.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        List<Autor> autorList = autorRepositorio.findAll();
        autorList.forEach(System.out::println);
    }

    private void  listarAutoresVivosDeterminadoAnio(){
        System.out.println("Ingresa el año de auto(es) vivos que desea buscar: ");
        int anio = sc.nextInt();
        List<Autor> autorList = autorRepositorio.aniosVidaAutor(anio);
        autorList.forEach(System.out::println);
    }

    private  void listarLibrosPorIdioma() {
        String banner = """ 
               Ingrese el idioma para buscar los libros:
               es- español
               en- inglés
               fr- francés
               pt- portugués
                """;
        System.out.println(banner);
        var idioma = sc.nextLine();
        var lenguaje = Lenguajes.fromString(idioma);
        List<Libros> librosList = libroRepositorio.findByidioma(lenguaje);
        if (librosList.isEmpty()){
            System.out.println("No se encontro el libro");
        }else{
            librosList.forEach(System.out::println);
        }
    }

}
