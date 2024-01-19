package domain.modelo.errores;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message) {
        super(message);
    }
}
