package org.example.graphql_davidroldan.domain.modelo;

import org.example.graphql_davidroldan.domain.modelo.Director;

import java.util.List;

public record Pelicula(Long id, String titulo, Integer anoLanzamiento, Integer duracion, Director director, List<Actor> actores, List<Genero> generos, List<Premio> premios) {
}
