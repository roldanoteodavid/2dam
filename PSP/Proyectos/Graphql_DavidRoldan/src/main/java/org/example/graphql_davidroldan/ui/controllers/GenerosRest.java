package org.example.graphql_davidroldan.ui.controllers;

import lombok.RequiredArgsConstructor;
import org.example.graphql_davidroldan.common.Constantes;
import org.example.graphql_davidroldan.domain.modelo.Genero;
import org.example.graphql_davidroldan.domain.modelo.Premio;
import org.example.graphql_davidroldan.domain.servicios.GeneroService;
import org.example.graphql_davidroldan.domain.servicios.PremiosService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GenerosRest {
    private final GeneroService generoService;

    @QueryMapping
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public List<Genero> getGeneros() {
        return generoService.getAll();
    }

    @MutationMapping
    @Secured(Constantes.ROLE_ADMIN)
    public void deleteGenero(@Argument Long id) {
        generoService.delete(id);
    }
}
