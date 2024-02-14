package org.example.graphql_davidroldan.data.repositories;

import org.example.graphql_davidroldan.data.modelo.ActorEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends ListCrudRepository<ActorEntity, Long> {
    List<ActorEntity> findAllByPeliculasActuadasIsNotNull();
}
