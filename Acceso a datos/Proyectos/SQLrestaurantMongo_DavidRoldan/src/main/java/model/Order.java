package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private LocalDateTime order_date;
    private int table_id;
    private List<OrderItem> order_items;

    public void setOrderItems(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    public List<OrderItem> getOrderItems() {
        return order_items;
    }

    public LocalDateTime getDate() {
        return order_date;
    }
}
