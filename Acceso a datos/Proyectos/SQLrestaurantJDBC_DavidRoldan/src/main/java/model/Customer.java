package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Customer(String fileLine) {
        String[] elemArray = fileLine.split(";");
        this.id = Integer.parseInt(elemArray[0]);
        this.firstname = elemArray[1];
        this.lastname = elemArray[2];
        this.email = elemArray[3];
        this.phone = elemArray[4];
        this.dob = LocalDate.parse(elemArray[5]);
    }

    public String toFileString() {
        return id + ";" + firstname + ";" + lastname + ";" + email + ";" + phone + ";" + dob;
    }
}
