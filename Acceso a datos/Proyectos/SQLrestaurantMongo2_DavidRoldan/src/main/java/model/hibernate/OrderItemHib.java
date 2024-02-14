package model.hibernate;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items", schema = "davidroldan_restaurant")
public class OrderItemHib {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_item_id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderHib order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItemHib menuItem;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
