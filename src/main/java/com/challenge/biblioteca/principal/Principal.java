package com.challenge.biblioteca.principal;

import com.challenge.biblioteca.model.Autor;
import com.challenge.biblioteca.model.DatosLibro;
import com.challenge.biblioteca.model.DatosResults;
import com.challenge.biblioteca.model.Libro;
import com.challenge.biblioteca.repository.AutorRepository;
import com.challenge.biblioteca.repository.LibroRepository;
import com.challenge.biblioteca.service.ConsumirAPI;
import com.challenge.biblioteca.service.ConvertirDatos;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    Scanner sc = new Scanner(System.in);
    ConsumirAPI consumirAPI = new ConsumirAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();
    LibroRepository libroRepository;
    AutorRepository autorRepository;
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menuPrincipal() {
        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    1.- Busca libro por titulo
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma                 
                    0- Salir
                    """;
            System.out.println(menu);
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
                    listarAutoresVivosEnUnaFecha();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opcion inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro a buscar: ");
        var libroBuscar = sc.nextLine();

        String json = consumirAPI.obtenerDatosAPI("/?search=" + URLEncoder.encode(libroBuscar));
        System.out.println(json);

        DatosResults resultados = convertirDatos.convertirDatos(json,DatosResults.class);

        Optional<DatosLibro> optionalDatosLibros =  resultados.results().stream()
                .filter(e -> e.titulo().toLowerCase().contains(libroBuscar.toLowerCase()))
                .findFirst();

        if (optionalDatosLibros.isPresent()){
            DatosLibro datosLibro = optionalDatosLibros.get();
            Libro libroExistente = libroRepository.findByTitulo(datosLibro.titulo());
            if (libroExistente != null){
                System.out.println("No se puede registrar el mismo libro más de una vez");
            }else{
                Libro libro = new Libro(datosLibro);
                Autor autor = libro.getAutor();
                Optional<Autor> autorExistente = libroRepository.obtenerAutorPorNombre(autor.getNombre());
                if (autorExistente.isPresent()) {
                    libro.setAutor(autorExistente.get());
                    libroRepository.save(libro);
                } else {
                    autorRepository.save(libro.getAutor());
                    libroRepository.save(libro);
                }
                System.out.println("----------LIBRO-----------");
                System.out.println(libro);
                System.out.println("--------------------------");
            }
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> librosRegistrados = libroRepository.findAll();
        for (Libro libro : librosRegistrados){
            System.out.println("""
                    ----------LIBRO-----------
                    Titulo: %s
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %s 
                    --------------------------
                    """.formatted(libro.getTitulo(),libro.getAutor().getNombre(),
                    libro.getIdioma(),libro.getNumeroDeDescargas()));
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = libroRepository.obtenerAutores();
        List<Libro> librosRegistrados;
        for (Autor autor : autores) {
            librosRegistrados = libroRepository.obtenerLibrosPorAutor(autor.getId());
            System.out.println("""
                ----------Autor-----------
                Nombre: %s
                Fecha de nacimiento: %s
                Fecha de fallecimiento: %s
                Libros: %s
                --------------------------
                """.formatted(autor.getNombre(), autor.getNacimiento(), autor.getFallecimiento(), librosRegistrados.stream()
                    .map(Libro::getTitulo)
                    .toList()));
        }
    }

    public void listarAutoresVivosEnUnaFecha(){
        System.out.println("Ingrese el año: ");
        var fecha = sc.nextInt();
        sc.nextLine();

        List<Autor> autores = libroRepository.obtenerAutoresPorFecha(fecha);
        for (Autor autor : autores){
            System.out.println("""
                    ----------Autor-----------
                    Nombre: %s
                    Fecha de nacimiento: %s
                    Fecha de fallecimiento: %s
                    --------------------------
                    """.formatted(autor.getNombre(),autor.getNacimiento(),autor.getFallecimiento()));
        }
    }

    public void listarLibrosPorIdioma(){
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es- español
                en- inglés
                fr- francés
                pt- portugés
                """);
        var libroIdioma = sc.nextLine();
        List<Libro> libros = libroRepository.obtenerLibrosPorIdioma(libroIdioma);
        if(!libros.isEmpty()){
            for(Libro libro : libros){
                System.out.println("""
                    ----------LIBRO-----------
                    Titulo: %s
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %s 
                    --------------------------
                    """.formatted(libro.getTitulo(),libro.getAutor().getNombre(),
                        libro.getIdioma(),libro.getNumeroDeDescargas()));
            }
        }else{
            System.out.println("No hay libros registrados para ese idioma");
        }
    }

}
