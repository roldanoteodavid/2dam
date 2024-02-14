package org.example.app.domain.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.app.common.Constantes;

@Data
@Entity
@Table(name = Constantes.VISUALIZADORES)
@AllArgsConstructor
@NoArgsConstructor
public class Visualizador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;

    @Column(name = Constantes.USER, nullable = false)
    private String username;

    @Column(name = Constantes.PASSWORD, nullable = false, columnDefinition = Constantes.TEXT)
    private String password;

    @ManyToOne
    @JoinColumn(name = Constantes.RECURSO_ID)
    private Recurso recurso;

}