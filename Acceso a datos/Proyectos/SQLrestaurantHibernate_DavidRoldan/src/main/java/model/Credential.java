package model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credentials", schema = "davidroldan_restaurant")
public class Credential {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "password", nullable = false)
    private String password;
}
