package model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    private Credential credential;

    public Customer(int id, String firstname, String lastname, String email, String phone, LocalDate dob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }
}
