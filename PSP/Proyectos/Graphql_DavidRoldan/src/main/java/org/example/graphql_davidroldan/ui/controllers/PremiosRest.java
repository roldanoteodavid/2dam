package org.example.graphql_davidroldan.ui.controllers;

import lombok.RequiredArgsConstructor;
import org.example.graphql_davidroldan.common.Constantes;
import org.example.graphql_davidroldan.domain.modelo.Premio;
import org.example.graphql_davidroldan.domain.servicios.PremiosService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/premios")
@RequiredArgsConstructor
public class PremiosRest {
    private final PremiosService premiosService;

    @QueryMapping
    @Secured({Constantes.ROLE_USER, Constantes.ROLE_ADMIN})
    public List<Premio> getPremios() {
        return premiosService.getAll();
    }

    @MutationMapping
    @Secured(Constantes.ROLE_ADMIN)
    public void deletePremio(@Argument Long id) {
        premiosService.delete(id);
    }
}
