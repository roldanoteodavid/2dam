package domain.modelo.errores;

import lombok.Data;

@Data
public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(String message) {
        super(message);
    }

}
