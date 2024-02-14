package org.example.graphql_davidroldan.data.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "actores")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nacionalidad;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @ManyToMany(mappedBy = "actores", fetch = FetchType.EAGER)
    private List<PeliculaEntity> peliculasActuadas;
}
