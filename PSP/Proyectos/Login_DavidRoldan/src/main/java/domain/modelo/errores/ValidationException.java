package domain.modelo.errores;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
