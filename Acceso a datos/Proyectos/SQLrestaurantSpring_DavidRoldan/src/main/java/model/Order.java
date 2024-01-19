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
    private int id;
    private LocalDateTime date;
    private int customer_id;
    private int table_id;
    private List<OrderItem> orderItems;

}
