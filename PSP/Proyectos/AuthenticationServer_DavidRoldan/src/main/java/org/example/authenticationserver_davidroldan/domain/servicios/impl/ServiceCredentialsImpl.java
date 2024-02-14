package org.example.authenticationserver_davidroldan.domain.servicios.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.example.authenticationserver_davidroldan.common.Constantes;
import org.example.authenticationserver_davidroldan.config.Configuration;
import org.example.authenticationserver_davidroldan.data.repositories.CredentialsRepository;
import org.example.authenticationserver_davidroldan.domain.modelo.Credentials;
import org.example.authenticationserver_davidroldan.domain.modelo.LoginTokens;
import org.example.authenticationserver_davidroldan.domain.modelo.errores.ValidationException;
import org.example.authenticationserver_davidroldan.domain.modelo.mappers.CredentialsEntityMapper;
import org.example.authenticationserver_davidroldan.domain.servicios.ServiceCredentials;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ServiceCredentialsImpl implements ServiceCredentials {

    private final Configuration config;
    private final PasswordEncoder passwordEncoder;
    private final CredentialsRepository credentialsRepository;
    private final CredentialsEntityMapper credentialsMapper;

    @Override
    public LoginTokens doLogin(String username, String password) {
        LoginTokens result = new LoginTokens();
        if (username == null || password == null) {
            throw new ValidationException(Constantes.USER_OR_PASSWORD_IS_EMPTY);
        } else {
            Credentials credentials = credentialsMapper.toCredentials(credentialsRepository.findByUsername(username));
            if (credentials.getUsername() != null && credentials.getPassword() != null) {
                if (passwordEncoder.matches(password, credentials.getPassword())) {
                    result.setAccessToken(generateAccessToken(username, credentials.getRole()));
                    result.setRefreshToken(generateRefreshToken(username, credentials.getRole()));
                } else {
                    throw new ValidationException(Constantes.USER_OR_PASSWORD_INCORRECT);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean singUp(Credentials c) {
        boolean result = false;
        Credentials credentials = credentialsMapper.toCredentials(credentialsRepository.findByUsername(c.getUsername()));
        if (credentials == null) {
            c.setPassword(passwordEncoder.encode(c.getPassword()));
            credentialsRepository.save(credentialsMapper.toCredentialsEntity(c));
            result = true;
        } else {
            throw new ValidationException(Constantes.USER_ALREADY_EXISTS);
        }
        return result;
    }

    @Override
    public LoginTokens refreshToken(String refreshToken) {
        if (refreshToken != null) {
            if (validateToken(refreshToken)) {
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(getPublicKey())
                        .build()
                        .parseClaimsJws(refreshToken);
                String username = claimsJws.getBody().getSubject();
                String role = claimsJws.getBody().get(Constantes.ROLE, String.class);
                return new LoginTokens(generateAccessToken(username, role), refreshToken);
            }else {
                throw new ValidationException(Constantes.TOKEN_EXPIRED);
            }
        }else {
            throw new ValidationException(Constantes.TOKEN_IS_EMPTY);
        }
    }

    private String generateAccessToken(String subject, String role) {
        return generateJWTToken(subject, role, Constantes.EXPIRATION_TIME_ACCESS_TOKEN);
    }

    private String generateRefreshToken(String subject, String role) {
        return generateJWTToken(subject, role, Constantes.EXPIRATION_TIME_REFRESH_TOKEN);
    }

    private String generateJWTToken(String subject, String role, int expirationSeconds) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(subject)
                .claim(Constantes.ROLE, role)
                .setIssuedAt(now)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expirationSeconds)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(config.getPathDatos())) {
                ks.load(fis, config.getPassword().toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(config.getPassword().toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(config.getUserkeystore(), pt);
            if (pkEntry != null) {
                return pkEntry.getPrivateKey();
            } else {
                throw new RuntimeException("No se encontró la entrada de clave privada en el keystore.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la clave privada del keystore.", e);
        }
    }


    private PublicKey getPublicKey() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(config.getPathDatos())) {
                ks.load(fis, config.getPassword().toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(config.getPassword().toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(config.getUserkeystore(), pt);
            if (pkEntry != null) {
                return pkEntry.getCertificate().getPublicKey();
            } else {
                throw new RuntimeException("No se encontró la entrada de clave privada en el keystore.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la clave pública del keystore.", e);
        }
    }

    private boolean validateToken(String refreshToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(refreshToken);

            long expirationMillis = claimsJws.getBody().getExpiration().getTime();
            return System.currentTimeMillis() < expirationMillis;

        } catch (ExpiredJwtException e) {
            throw new ValidationException("Token expirado");
        }
    }
}
