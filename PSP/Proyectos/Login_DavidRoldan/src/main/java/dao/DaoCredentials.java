package dao;

import domain.modelo.Credentials;
import domain.modelo.errores.ApiError;
import io.vavr.control.Either;


public interface DaoCredentials {

    Credentials get(String user);
    Credentials getByAccess(String accesstoken);

    int save(Credentials c);

    boolean setRefreshToken(Credentials c);
    boolean setTwoFactorCode(Credentials c);

    Credentials refreshToken(Credentials credentials);

    boolean forgotPassword(Credentials c);

    boolean changePassword(Credentials c);

    Either<ApiError, Integer> activarUsuario(String codigo, String nuevocodigo);
}
