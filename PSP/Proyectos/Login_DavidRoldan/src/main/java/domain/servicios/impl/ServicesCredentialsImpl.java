package domain.servicios.impl;

import common.Constants;
import dao.DaoCredentials;
import domain.modelo.Credentials;
import domain.modelo.errores.ApiError;
import domain.modelo.errores.Exception401;
import domain.modelo.errores.ValidationException;
import domain.servicios.ServicesCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.vavr.control.Either;
import jakarta.Utils;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ServicesCredentialsImpl implements ServicesCredentials {

    private final DaoCredentials dao;
    private final Pbkdf2PasswordHash passwordHash;
    private final KeyProvider keyProvider;

    private final MandarMail mail;

    @Inject
    public ServicesCredentialsImpl(DaoCredentials dao, Pbkdf2PasswordHash passwordHash, KeyProvider keyProvider, MandarMail mail) {
        this.dao = dao;
        this.passwordHash = passwordHash;
        this.keyProvider = keyProvider;
        this.mail = mail;
    }

    @Override
    public Credentials get(String user) {
        return dao.get(user);
    }

    @Override
    public Credentials doLogin(String user, String password) {
        Credentials result = null;
        if (user == null || password == null) {
            throw new ValidationException(Constants.USER_OR_PASSWORD_IS_EMPTY);
        } else {
            Credentials credentials = dao.get(user);
            if (credentials.getUsername() != null && credentials.getPassword() != null) {
                if (passwordHash.verify(password.toCharArray(), credentials.getPassword())) {
                    result = credentials;
                    result.setTemporalCode(Utils.randomBytes());
                    if (!dao.setTwoFactorCode(result)) {
                        result = null;
                    } else {
                        try {
                            mail.generateAndSendEmail(result.getEmail(), Constants.OPEN_HTML+result.getUsername()+Constants.MAIL_TWOFA + result.getTemporalCode() + Constants.CLOSE_HTML, Constants.TWOFACTAUTH);
                        } catch (Exception e) {
                            throw new ValidationException(e.getMessage());
                        }
                    }
                } else {
                    throw new ValidationException(Constants.USER_OR_PASSWORD_INCORRECT);
                }
            }
        }
        return result;
    }

    @Override
    public Credentials twoFactorAuth(Credentials c) {
        Credentials result = null;
        if (c.getUsername() == null || c.getTemporalCode() == null) {
            throw new ValidationException(Constants.USER_OR_TEMPORAL_CODE_IS_EMPTY);
        } else {
            Credentials credentials = dao.get(c.getUsername());
            if (credentials.getUsername() != null && credentials.getTemporalCode() != null) {
                if (c.getTemporalCode().equals(credentials.getTemporalCode())) {
                    result = credentials;
                    result.setAccessToken(generateAccessToken(c.getUsername(), Constants.ISSUER));
                    result.setRefreshToken(generateRefreshToken(c.getUsername(), Constants.ISSUER));
                    if (!dao.setRefreshToken(result)) {
                        result = null;
                    }
                } else {
                    throw new ValidationException(Constants.TWOFA_CODE_INCORRECT);
                }
            }
        }
        return result;
    }

    @Override
    public int save(Credentials c) {
        String password = passwordHash.generate(c.getPassword().toCharArray());
        c.setPassword(password);
        int result = dao.save(c);
        if (result == 1) {
            try {
                mail.generateAndSendEmail(c.getEmail(), Constants.HTML_HAGA_A_HREF_HTTP_LOCALHOST_8080_LOGIN_DAVID_ROLDAN_1_0_SNAPSHOT_ACTIVACION_CODIGO + c.getTemporalCode() + Constants.CLICK_AQUI_A_PARA_ACTIVAR_SU_CUENTA_HTML, Constants.VALIDA_TU_CORREO);
            } catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Credentials refreshToken(String token) {
        validateToken(token);
        Credentials credentials = new Credentials();
        credentials.setRefreshToken(token);
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(keyProvider.getKey()).build().parseClaimsJws(token);
        credentials.setAccessToken(generateAccessToken(claimsJws.getBody().getSubject(), claimsJws.getBody().getIssuer()));
        return dao.refreshToken(credentials);
    }

    @Override
    public boolean forgotPassword(String username) {
        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        String temporalCode = Utils.randomBytes();
        String password = passwordHash.generate(temporalCode.toCharArray());
        credentials.setTemporalCode(password);
        Credentials c = dao.get(username);
        if (dao.forgotPassword(credentials)) {
            try {
                mail.generateAndSendEmail(c.getEmail(), Constants.HTML_SU_NUEVA_CONTRASENYA_TEMPORAL_ES + temporalCode + Constants.HTML, Constants.CONTRASENYA_TEMPORAL);
            } catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changePassword(Credentials c) {
        Credentials credentials = dao.get(c.getUsername());
        if (passwordHash.verify(c.getTemporalCode().toCharArray(), credentials.getTemporalPassword())) {
            String password = passwordHash.generate(c.getPassword().toCharArray());
            c.setPassword(password);
            return dao.changePassword(c);
        } else {
            throw new ValidationException(Constants.INCORRECT_TEMPORAL_PASSWORD);
        }
    }

    @Override
    public Credentials validateAccess(Credentials c) {
        Credentials credentials = dao.getByAccess(c.getAccessToken());
        credentials.setAccessToken(c.getAccessToken());
        validateToken(credentials.getAccessToken());
        return credentials;
    }

    @Override
    public Either<ApiError, Integer> activarUsuario(String codigo) {
        String nuevocodigo = Utils.randomBytes();
        Either<ApiError, Integer> result = dao.activarUsuario(codigo, nuevocodigo);
        if (result.isLeft()) {
            ApiError error = result.getLeft();
            if (error.getMessage().contains(Constants.EL_CODIGO_DE_EXPIRACION_HA_CADUCADO_HAS_RECIBIDO_UN_NUEVO_CORREO_EN)) {
                String[] parts = error.getMessage().split(" ");
                String email = parts[parts.length - 1];
                try {
                    mail.generateAndSendEmail(email, Constants.HTML_HAGA_A_HREF_HTTP_LOCALHOST_8080_LOGIN_DAVID_ROLDAN_1_0_SNAPSHOT_ACTIVACION_CODIGO + nuevocodigo + Constants.CLICK_AQUI_A_PARA_ACTIVAR_SU_CUENTA_EN_LOS_PROXIMOS_5_MINUTOS_HTML, Constants.VALIDA_TU_CORREO);
                } catch (Exception e) {
                    throw new ValidationException(e.getMessage());
                }
            }
        }
        return result;
    }

    private String generateAccessToken(String subject, String issuer) {
        return generateJWTToken(subject, issuer, Constants.EXPIRATION_TIME_ACCESS_TOKEN);
    }

    private String generateRefreshToken(String subject, String issuer) {
        return generateJWTToken(subject, issuer, Constants.EXPIRATION_TIME_REFRESH_TOKEN);
    }

    private String generateJWTToken(String subject, String issuer, int expirationSeconds) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expirationSeconds)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(keyProvider.getKey()).compact();
    }

    private boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(keyProvider.getKey()).build().parseClaimsJws(token);

            long expirationMillis = claimsJws.getBody().getExpiration().getTime();

            if (System.currentTimeMillis() <= expirationMillis) return true;
            else throw new Exception401(Constants.TOKEN_HAS_EXPIRED);
        } catch (Exception e) {
            throw new Exception401(Constants.TOKEN_INVALID);
        }
    }
}
