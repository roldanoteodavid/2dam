package model;

import lombok.*;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private ObjectId _id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private LocalDate date_of_birth;
    private List<Order> orders;

    public ObjectId getId() {
        return _id;
    }

    public Customer(String id, String firstname, String lastname, String email, String phone, LocalDate dob) {
        this._id = new ObjectId(id);
        this.first_name = firstname;
        this.last_name = lastname;
        this.email = email;
        this.phone = phone;
        this.date_of_birth = dob;
    }
}
