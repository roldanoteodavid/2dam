package jakarta.rest;

import common.Constants;
import domain.modelo.Credentials;
import domain.servicios.ServicesCredentials;
import jakarta.Utils;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(Constantes.LOGINPATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private final ServicesCredentials serviceCredentiales;

    @Inject
    public RestLogin(ServicesCredentials serviceCredentiales) {
        this.serviceCredentiales = serviceCredentiales;
    }

    @Path(Constantes.REGISTERPATH)
    @POST
    public Response addCredential(Credentials credentials) {
        credentials.setTemporalCode(Utils.randomBytes());
        int result = serviceCredentiales.save(credentials);
        if (result == 1) {
            return Response.status(Response.Status.CREATED).entity(credentials).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path(Constantes.LOGINPATH)
    @POST
    public Response login(Credentials credentials) {
        Credentials result = serviceCredentiales.doLogin(credentials.getUsername(), credentials.getPassword());
        if (result != null) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path(Constantes.TWOFACTORAUTH)
    @POST
    public Response twoFactorAuth(Credentials credentials) {
        Credentials result = serviceCredentiales.twoFactorAuth(credentials);
        if (result != null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .header(Constantes.AUTHORIZATION, result.getAccessToken())
                    .header(Constantes.REFRESHTOKEN, result.getRefreshToken())
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path(Constantes.REFRESHPATH)
    @GET
    public Response refreshToken(@QueryParam(Constantes.REFRESH_TOKEN) String refreshToken) {
        Credentials result = serviceCredentiales.refreshToken(refreshToken);
        if (result != null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .header(Constantes.AUTHORIZATION, result.getAccessToken())
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path(Constantes.FORGOTPASSWORDPATH)
    @GET
    public Response forgotPassword(@QueryParam(Constants.USERNAME) String username) {
        boolean result = serviceCredentiales.forgotPassword(username);
        if (result) {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path(Constantes.CHANGEPASSWORDPATH)
    @POST
    public Response changePassword(Credentials credentials) {
        boolean result = serviceCredentiales.changePassword(credentials);

        if (result) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

}
