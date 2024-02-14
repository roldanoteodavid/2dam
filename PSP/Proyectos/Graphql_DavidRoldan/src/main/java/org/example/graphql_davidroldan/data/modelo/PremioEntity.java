package org.example.graphql_davidroldan.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "premios")
public class PremioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private int ano;

    @ManyToMany(mappedBy = "premios", fetch = FetchType.EAGER)
    private List<PeliculaEntity> peliculasGanadoras;
}
