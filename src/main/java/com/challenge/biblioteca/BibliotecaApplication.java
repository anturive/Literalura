package com.challenge.biblioteca;

import com.challenge.biblioteca.principal.Principal;
import com.challenge.biblioteca.repository.AutorRepository;
import com.challenge.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {
	@Autowired
	LibroRepository libroRepository;
	@Autowired
	AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.menuPrincipal();
	}
}
