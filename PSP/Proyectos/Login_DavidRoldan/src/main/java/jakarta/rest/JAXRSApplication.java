package jakarta.rest;


import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath(Constantes.API)
@DeclareRoles({Constantes.ROLE_ADMIN, Constantes.ROLE_USER})
public class JAXRSApplication extends Application {

}
