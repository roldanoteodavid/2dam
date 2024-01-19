package domain.modelo.errores;

import lombok.Data;

@Data
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

}
