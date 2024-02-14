package org.example.app.dao;

import org.example.app.domain.modelo.Recurso;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosRepository extends
        ListCrudRepository<Recurso, Integer> {
}
