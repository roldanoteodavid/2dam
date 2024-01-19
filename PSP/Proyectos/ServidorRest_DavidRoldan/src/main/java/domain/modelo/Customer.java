package domain.modelo;

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
}
