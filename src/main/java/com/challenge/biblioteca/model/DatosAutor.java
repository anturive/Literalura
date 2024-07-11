package com.challenge.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        Integer nacimiento,
        @JsonAlias("death_year")
        Integer fallecimiento
) {
}