package model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "davidroldan_restaurant")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int id;

    @Column(name = "order_date")
    private LocalDateTime date;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "table_id")
    private int table_id;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE }/*, fetch = FetchType.EAGER*/)
    private List<OrderItem> orderItems;

    public Order(int id, LocalDateTime date, int customer_id, int table_id) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.table_id = table_id;
    }
}
