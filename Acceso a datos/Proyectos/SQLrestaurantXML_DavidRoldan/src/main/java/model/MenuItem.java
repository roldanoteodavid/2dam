package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuItem {
    private int id;
    private String name;
    private int price;

    @Override
    public String toString() {
        return name;
    }
}
