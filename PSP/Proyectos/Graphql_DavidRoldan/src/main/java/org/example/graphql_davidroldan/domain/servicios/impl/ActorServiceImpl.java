package org.example.graphql_davidroldan.domain.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.graphql_davidroldan.common.Constantes;
import org.example.graphql_davidroldan.data.modelo.ActorEntity;
import org.example.graphql_davidroldan.data.repositories.ActorRepository;
import org.example.graphql_davidroldan.domain.modelo.Actor;
import org.example.graphql_davidroldan.domain.modelo.errores.ValidationException;
import org.example.graphql_davidroldan.domain.modelo.mappers.ActorEntityMapper;
import org.example.graphql_davidroldan.domain.servicios.ActoresService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ActorServiceImpl implements ActoresService {

    private final ActorRepository actorRepository;
    private final ActorEntityMapper actorMapper;

    @Override
    public List<Actor> getAll() {
        List<ActorEntity> all = actorRepository.findAll();
        log.info("Actores encontrados: " + all.size());
        List<Actor> collect = all.stream().map(actorMapper::toActor).toList();
        return collect;
//        return actorRepository.findAllByPeliculasActuadasIsNotNull().stream().map(actorMapper::toActor).toList();
    }

    @Override
    public Boolean delete(long id) {
        if (actorRepository.findById(id).isPresent()) {
            actorRepository.deleteById(id);
            return true;
        } else {
            throw new ValidationException(Constantes.LA_PELICULA_NO_EXISTE);
        }
    }
}
