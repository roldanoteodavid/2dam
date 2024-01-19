package jakarta.security;

import domain.modelo.Credentials;
import domain.servicios.ServicesCredentials;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;


public class InMemoryIdentityStore implements IdentityStore {

    private final ServicesCredentials servicesCredentials;

    @Inject
    public InMemoryIdentityStore(ServicesCredentials servicesCredentials) {
        this.servicesCredentials = servicesCredentials;
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        TokenCredentials tokens = (TokenCredentials) credential;
        Credentials credentials = new Credentials();
        if (tokens.getAccessToken() != null) {
            credentials.setAccessToken(tokens.getAccessToken());
            try {
                Credentials c = servicesCredentials.validateAccess(credentials);
                if (c!=null) {
                    return new CredentialValidationResult
                            (c.getUsername(), Collections.singleton(c.getRole()));
                } else return INVALID_RESULT;
            } catch (Exception e){
                return CredentialValidationResult.NOT_VALIDATED_RESULT;
            }

        }
        return INVALID_RESULT;
    }

}
