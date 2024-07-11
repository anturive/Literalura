package com.challenge.biblioteca.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumirAPI {
    private final String BASE_URL = "https://gutendex.com/books";

    public String obtenerDatosAPI(String url){
        HttpResponse<String> response = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
