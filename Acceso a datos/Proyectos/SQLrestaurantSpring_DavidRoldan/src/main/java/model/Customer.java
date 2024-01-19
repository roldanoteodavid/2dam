package model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate dob;
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
