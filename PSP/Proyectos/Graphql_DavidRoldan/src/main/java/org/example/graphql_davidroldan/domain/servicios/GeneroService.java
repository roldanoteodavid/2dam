package org.example.graphql_davidroldan.domain.servicios;

import org.example.graphql_davidroldan.domain.modelo.Genero;

import java.util.List;


public interface GeneroService {
    List<Genero> getAll();
    Boolean delete(long id);
}
