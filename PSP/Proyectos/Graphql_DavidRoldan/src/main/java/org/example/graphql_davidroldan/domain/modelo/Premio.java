package org.example.graphql_davidroldan.domain.modelo;

import java.util.List;

public record Premio(Long id, String nombre, String categoria, Integer ano, List<Pelicula> peliculasGanadoras) {
}
