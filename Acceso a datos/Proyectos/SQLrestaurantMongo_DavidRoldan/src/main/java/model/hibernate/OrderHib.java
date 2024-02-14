package model.hibernate;

import jakarta.persistence.Table;
import jakarta.persistence.*;
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
public class OrderHib {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int id;

    @Column(name = "order_date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerHib customer;

    @Column(name = "table_id")
    private int table_id;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE }/*, fetch = FetchType.EAGER*/)
    private List<OrderItemHib> orderItems;

}
