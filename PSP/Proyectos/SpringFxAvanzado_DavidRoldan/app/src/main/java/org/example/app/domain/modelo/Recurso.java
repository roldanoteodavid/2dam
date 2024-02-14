package org.example.app.domain.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.app.common.Constantes;

import java.util.List;

@Data
@Entity
@Table(name = Constantes.RECURSOS)
@AllArgsConstructor
@NoArgsConstructor
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;

    @Column(name = Constantes.NOMBRE, nullable = false)
    private String nombre;

    @Column(name = Constantes.PASSWORD, nullable = false)
    private String password;

    @Column(name = Constantes.FIRMA, nullable = false, columnDefinition = Constantes.TEXT)
    private String firma;

    @Column(name = Constantes.USER_FIRMA, nullable = false)
    private String userFirma;

    @OneToMany(mappedBy = Constantes.RECURSO)
    private List<Visualizador> visualizadores;
}


