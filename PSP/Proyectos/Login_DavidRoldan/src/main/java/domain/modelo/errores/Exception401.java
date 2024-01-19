package domain.modelo.errores;

import lombok.Data;

@Data
public class Exception401 extends RuntimeException {

    public Exception401(String message) {
        super(message);
    }

}
