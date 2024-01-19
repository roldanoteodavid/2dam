package domain.modelo.errores;

import lombok.Data;

@Data
public class HasOrdersException extends RuntimeException {

    public HasOrdersException(String message) {
        super(message);
    }

}
