package domain.modelo.errores;


public class EmptyListException extends RuntimeException {


    public EmptyListException(String error) {
        super(error);
    }
}
