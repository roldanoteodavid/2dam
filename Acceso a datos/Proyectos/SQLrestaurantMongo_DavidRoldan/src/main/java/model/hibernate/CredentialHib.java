package model.hibernate;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credentials", schema = "davidroldan_restaurant")
public class CredentialHib {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "password", nullable = false)
    private String password;
}
