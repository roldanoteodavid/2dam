package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Order {
    private int id;
    private LocalDateTime date;
    private int customer_id;
    private int table_id;

    public Order(int id, LocalDateTime date, int customer_id, int table_id) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.table_id = table_id;
    }

    public Order(String fileLine) {
        String[] elemArray = fileLine.split(";");
        this.id = Integer.parseInt(elemArray[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.parse(elemArray[1], formatter);
        this.customer_id = Integer.parseInt(elemArray[2]);
        this.table_id = Integer.parseInt(elemArray[3]);
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter);
        return id + ";" + formattedDate + ";" + customer_id + ";" + table_id;
    }
}
