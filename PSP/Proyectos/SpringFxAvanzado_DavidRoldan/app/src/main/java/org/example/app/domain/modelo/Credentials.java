package org.example.app.domain.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.app.common.Constantes;

@Data
@Entity
@Table(name = Constantes.CREDENTIALS)
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;

    @Column(name = Constantes.USERNAME, nullable = false)
    private String username;

    @Column(name = Constantes.PASSWORD, nullable = false)
    private String password;
}
