package jakarta.errores;

import domain.modelo.errores.ApiError;
import domain.modelo.errores.EmptyListException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class Exception401Mapper implements ExceptionMapper<EmptyListException> {
    @Override
    public Response toResponse(EmptyListException e) {
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.UNAUTHORIZED).entity(apiError).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
