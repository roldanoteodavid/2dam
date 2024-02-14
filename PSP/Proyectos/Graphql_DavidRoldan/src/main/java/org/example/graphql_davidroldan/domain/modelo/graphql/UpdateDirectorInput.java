package org.example.graphql_davidroldan.domain.modelo.graphql;

import java.util.Date;

public record UpdateDirectorInput(
        Long id, String nombre, String nacionalidad, Date fechaNacimiento
        ) {
}
