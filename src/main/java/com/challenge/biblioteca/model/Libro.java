package com.challenge.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",length = 300,unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Autor autor;
    @Column(name = "idioma")
    private String idioma;
    @Column(name="descargas")
    private Double numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        Autor autor = new Autor(datosLibro.autores().get(0));
        this.autor = autor;
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + '\n' +
                "Autor: " + autor.getNombre() + '\n' +
                "Idioma: " + idioma + '\n' +
                "Numero de descargas: " + numeroDeDescargas;
    }
}
