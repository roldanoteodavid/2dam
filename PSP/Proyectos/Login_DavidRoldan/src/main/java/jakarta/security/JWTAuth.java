package jakarta.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.rest.Constantes;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;


@ApplicationScoped
public class JWTAuth implements HttpAuthenticationMechanism {


    private final InMemoryIdentityStore identity;

    @Inject
    public JWTAuth(InMemoryIdentityStore identity) {
        this.identity = identity;
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpMessageContext httpMessageContext) {

        if (!httpMessageContext.isProtected()) {
            return httpMessageContext.doNothing();
        }
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(Constantes.BEARER)) {
            String token = authorizationHeader.substring(Constantes.BEARER.length());

            TokenCredentials tokenCredentials = new TokenCredentials(token, null);
            CredentialValidationResult validationResult = identity.validate(tokenCredentials);

            if (validationResult.getStatus() == CredentialValidationResult.Status.VALID) {
                return httpMessageContext.notifyContainerAboutLogin(validationResult);
            } else if (validationResult.getStatus() == CredentialValidationResult.Status.NOT_VALIDATED) {
                return httpMessageContext.responseUnauthorized();
            }
        }

        return httpMessageContext.responseUnauthorized();
    }
}
