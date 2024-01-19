package domain.modelo.errores;

public class ErrorConnectingDatabaseException extends RuntimeException {


    public ErrorConnectingDatabaseException(String message) {
        super(message);
    }
}
