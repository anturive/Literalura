package com.challenge.biblioteca.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos implements IConvertirDatos{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String json, Class<T> clases) {
        try {
            return mapper.readValue(json, clases);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}