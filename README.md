# Literalura Challenge Alura Back End

## Introducción

Esta aplicación Java utiliza Spring Boot para gestionar libros y autores, permitiendo consultas a una API externa (Gutendex.com) para obtener información detallada de libros. Los datos se almacenan en una base de datos PostgreSQL utilizando Spring Data JPA.

## Tecnologias
- Java 17: Lenguaje de programación principal.
- Spring Boot: Framework para desarrollo de aplicaciones Java.
- PostgreSQL: Sistema de gestión de bases de datos relacional.
- Maven: Herramienta de gestión de proyectos.
- Gutendex API: API externa para información de libros.

## Dependencias principales usadas
- Spring Data JPA: Para la persistencia de datos.
- PostgreSQL Driver: Para la conexión con la base de datos PostgreSQL.

## API
La aplicación consume la API Gutendex.com para obtener información detallada de los libros, que incluye título, datos del autor, idioma y número de descargas.

## Advertencias
No se puede registrar el mismo libro 2 veces

## Intrucciones

![Menu Principal](img/menu.png)

- Opción 1:
    Ingresar el nombre correcto del libro a buscar
    
    ![Opcion1](img/opcion1.png)

- Opción 2:
    Información de todos los libros en la Base de datos
    
    ![Opcion2](img/opcion2.png)

- Opción 3:
    Muestra todos los autores registrados junto con la lista de sus libros
    
    ![Opcion3](img/opcion3.png)

- Opción 4:
    Ingresar un año determinado para buscar los autores vivos
    
    ![Opcion4](img/opcion4.png)

- Opción 5:
    Ingresar la abreviatura del idioma para listar libros con el respectivo idioma elegido
    
    ![Opcion5](img/opcion5.png)
