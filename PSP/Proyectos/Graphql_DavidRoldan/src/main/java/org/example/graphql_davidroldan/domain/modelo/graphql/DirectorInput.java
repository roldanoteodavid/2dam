package org.example.graphql_davidroldan.domain.modelo.graphql;

import java.util.Date;

public record DirectorInput(
        String nombre, String nacionalidad, Date fechaNacimiento
        ) {
}
