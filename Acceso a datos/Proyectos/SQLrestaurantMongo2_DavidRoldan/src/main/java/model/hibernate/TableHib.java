package model.hibernate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "restaurant_tables", schema = "davidroldan_restaurant")
public class TableHib {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "table_number_id", nullable = false)
    private int id;

    @Column(name = "number_of_seats", nullable = false)
    private int numberofseats;
}
