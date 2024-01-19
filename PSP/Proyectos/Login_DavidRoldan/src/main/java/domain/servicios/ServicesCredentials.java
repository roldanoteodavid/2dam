package domain.servicios;

import domain.modelo.Credentials;
import domain.modelo.errores.ApiError;
import io.vavr.control.Either;

public interface ServicesCredentials {
    Credentials get(String user);

    Credentials doLogin(String user, String password);
    Credentials twoFactorAuth(Credentials credentials);

    int save(Credentials c);

    Credentials refreshToken(String token);

    boolean forgotPassword(String username);

    boolean changePassword(Credentials c);

    Credentials validateAccess(Credentials c);

    Either<ApiError, Integer> activarUsuario(String codigo);
}
