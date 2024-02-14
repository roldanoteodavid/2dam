package org.example.graphql_davidroldan.domain.servicios.impl;

import lombok.RequiredArgsConstructor;
import org.example.graphql_davidroldan.common.Constantes;
import org.example.graphql_davidroldan.data.repositories.PremioRepository;
import org.example.graphql_davidroldan.domain.modelo.Premio;
import org.example.graphql_davidroldan.domain.modelo.errores.ValidationException;
import org.example.graphql_davidroldan.domain.modelo.mappers.PremioEntityMapper;
import org.example.graphql_davidroldan.domain.servicios.PremiosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements PremiosService {

    private final PremioRepository premioRepository;
    private final PremioEntityMapper premioMapper;

    @Override
    public List<Premio> getAll() {
        return premioRepository.findAll().stream().map(premioMapper::toPremio).toList();
    }

    @Override
    public Boolean delete(long id) {
        if (premioRepository.findById(id).isPresent()) {
            premioRepository.deleteById(id);
            return true;
        } else {
            throw new ValidationException(Constantes.LA_PELICULA_NO_EXISTE);
        }
    }
}
