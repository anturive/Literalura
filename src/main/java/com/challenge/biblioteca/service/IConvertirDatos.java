package com.challenge.biblioteca.service;

public interface IConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clases);
}
