package org.example.graphql_davidroldan.domain.servicios;

import org.example.graphql_davidroldan.domain.modelo.Actor;

import java.util.List;


public interface ActoresService {
    List<Actor> getAll();
    Boolean delete(long id);
}
