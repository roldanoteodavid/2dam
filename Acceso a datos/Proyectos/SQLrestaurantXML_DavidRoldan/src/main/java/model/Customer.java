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
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private LocalDate dob;

    public Customer(String fileLine) {
        String[] elemArray = fileLine.split(";");
        this.id = Integer.parseInt(elemArray[0]);
        this.first_name = elemArray[1];
        this.last_name = elemArray[2];
        this.email = elemArray[3];
        this.phone = elemArray[4];
        this.dob = LocalDate.parse(elemArray[5]);
    }

    public String toFileString() {
        return id + ";" + first_name + ";" + last_name + ";" + email + ";" + phone + ";" + dob;
    }
}
