package org.example.graphql_davidroldan.domain.modelo;

import java.util.Date;
import java.util.List;

public record Actor(Long id, String nombre, String nacionalidad, Date fechaNacimiento, List<Pelicula> peliculasActuadas) {
}
