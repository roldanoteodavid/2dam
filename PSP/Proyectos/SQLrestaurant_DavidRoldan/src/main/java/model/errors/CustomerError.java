package model.errors;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public sealed class CustomerError permits CustomerErrorEmptyList {
    private final String message;

    private final LocalDateTime date;

    public CustomerError(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
