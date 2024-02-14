package org.example.app.dao;

import org.example.app.domain.modelo.Visualizador;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisualizadoresRepository extends
        ListCrudRepository<Visualizador, Integer> {
    List<Visualizador> findByUsername(String username);
    Visualizador findByRecursoIdAndUsername(int idrecurso, String username);

}
