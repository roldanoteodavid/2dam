package org.example.graphql_davidroldan.domain.modelo;

import org.example.graphql_davidroldan.domain.modelo.Pelicula;

import java.util.List;

public record Genero(Long id, String nombre, List<Pelicula> peliculas) {
}
