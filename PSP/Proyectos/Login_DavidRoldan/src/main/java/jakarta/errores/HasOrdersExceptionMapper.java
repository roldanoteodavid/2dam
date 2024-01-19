package jakarta.errores;

import domain.modelo.errores.ApiError;
import domain.modelo.errores.HasOrdersException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;


@Provider
public class HasOrdersExceptionMapper implements ExceptionMapper<HasOrdersException> {

    public Response toResponse(HasOrdersException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.UNAUTHORIZED).entity(apiError).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
