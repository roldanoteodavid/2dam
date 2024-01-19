package jakarta.errores;

public class AlgorithmNotSupportedException extends RuntimeException {
    public AlgorithmNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
