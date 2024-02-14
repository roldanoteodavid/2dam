package model.errors;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestaurantError {
    private final String message;

    private final LocalDateTime date;

    public RestaurantError(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
