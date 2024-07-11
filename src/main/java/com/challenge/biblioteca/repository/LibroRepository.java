package com.challenge.biblioteca.repository;

import com.challenge.biblioteca.model.Autor;
import com.challenge.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Libro findByTitulo(String titulo);
    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autor a")
    List<Autor> obtenerAutores();
    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.nombre = :nombre")
    Optional<Autor> obtenerAutorPorNombre(String nombre);
    @Query("SELECT l FROM Libro l WHERE l.autor.id = :autorId")
    List<Libro> obtenerLibrosPorAutor(Long autorId);
    @Query("SELECT a FROM Libro  l JOIN l.autor a WHERE a.nacimiento <= :fecha AND a.fallecimiento >= :fecha")
    List<Autor> obtenerAutoresPorFecha(Integer fecha);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> obtenerLibrosPorIdioma(String idioma);
}
