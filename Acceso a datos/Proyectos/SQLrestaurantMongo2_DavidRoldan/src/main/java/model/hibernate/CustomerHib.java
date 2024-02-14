package model.hibernate;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customers")
@NamedQuery(name = "CustomerHib.getAll", query = "SELECT c FROM CustomerHib c LEFT JOIN FETCH c.orders o LEFT JOIN FETCH o.orderItems")
public class CustomerHib {
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

    @OneToMany(mappedBy = "customer")
    private List<OrderHib> orders;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    private CredentialHib credential;

    public CustomerHib(int id, String firstname, String lastname, String email, String phone, LocalDate dob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }
}
