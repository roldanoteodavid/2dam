package org.example.graphql_davidroldan.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "directores")
public class DirectorEntity {
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

    @OneToMany(mappedBy = "director")
    private List<PeliculaEntity> peliculasDirigidas;
}
