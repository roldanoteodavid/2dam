package org.example.graphql_davidroldan.domain.servicios;

import org.example.graphql_davidroldan.domain.modelo.Premio;

import java.util.List;


public interface PremiosService {
    List<Premio> getAll();
    Boolean delete(long id);
}
