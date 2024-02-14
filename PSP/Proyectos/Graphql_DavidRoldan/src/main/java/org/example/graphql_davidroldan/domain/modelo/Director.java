package org.example.graphql_davidroldan.domain.modelo;

import java.util.Date;
import java.util.List;

public record Director(Long id, String nombre, String nacionalidad, Date fechaNacimiento/*, List<Pelicula> peliculasDirigidas*/){
}
